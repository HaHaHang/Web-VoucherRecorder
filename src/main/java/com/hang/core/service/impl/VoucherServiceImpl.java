package com.hang.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hang.core.dao.SubjectMapper;
import com.hang.core.dao.VoucherMapper;
import com.hang.core.dao.Voucher_ItMapper;
import com.hang.core.entity.Voucher;
import com.hang.core.entity.VoucherKey;
import com.hang.core.entity.Voucher_It;
import com.hang.core.pojo.VoucherKeyPlus;
import com.hang.core.pojo.VoucherRecord;
import com.hang.core.pojo.VoucherSummaryIt;
import com.hang.core.pojo.VoucherSummaryVo;
import com.hang.core.pojo.Voucher_ItRecord;
import com.hang.core.pojo.Voucher_It_Plus;
import com.hang.core.service.VoucherService;
import com.hang.core.util.VoucherItPlusSortUtil;
@Service
public class VoucherServiceImpl implements VoucherService {

	@Autowired
	VoucherMapper voucherMapper;
	@Autowired
	Voucher_ItMapper itMapper;
	@Autowired
	SubjectMapper subMapper;
	
	@Override
	public int getMAXValueOfItemId(VoucherKey key) throws Exception{
		Integer count = voucherMapper.getMAXValueOfItemId(key);
		if(count != null){
			return (voucherMapper.getMAXValueOfItemId(key)+1);
		}else{
			return 1;
		}
	}

	@Override
	public boolean insertVoucher(VoucherRecord voucherRecord) throws Exception{
		if(voucherMapper.insertSelective(voucherRecord.getVoucher())==1){
			short voucherid = voucherMapper.getVoucherIdByVoucher(voucherRecord.getVoucher());
			if(voucherid>0){
				for (Voucher_It it : voucherRecord.getVoucher_it()) {
					it.setVoucherid(voucherid);
					if(itMapper.insert(it)!=1){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	

	@Override
	public List<VoucherKey> selectAllVoucherKey(Short param) throws Exception{
		return voucherMapper.listAccountPeriodByBooksetId(param);
	}

	@Override
	public List<Voucher_ItRecord> selectAllVoucher(VoucherKey key) throws Exception{
		List<Voucher> list = voucherMapper.listVoucherByKey(key);
		List<Voucher_ItRecord> recordList = new ArrayList<Voucher_ItRecord>();
		for (Voucher li : list) {
			List<Voucher_It_Plus> itRecord =  itMapper.listVoucherItPlusByVoucherId(li);
			if(itRecord.get(0).getNote().isEmpty()){
				itRecord = VoucherItPlusSortUtil.Sort(itRecord);
			}
			for(Voucher_It_Plus it : itRecord){
				Voucher_ItRecord itRe = new Voucher_ItRecord();
				itRe.setVoucher(li);
				itRe.setVoucher_it(it);
				recordList.add(itRe);
			}
			
		}
		return recordList;
	}

	
	
	@Override
	public boolean updateVoucher(VoucherRecord record) throws Exception{
		if(voucherMapper.updateByPrimaryKeySelective(record.getVoucher())>0){
			if(itMapper.deletVoucherItem(record.getVoucher().getVoucherid())>0){
				for (Voucher_It it : record.getVoucher_it()) {
					it.setVoucherid(record.getVoucher().getVoucherid());
					if(itMapper.insert(it)<0){
						return false;
					}
				}
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean deleteVoucher(Voucher voucher) throws Exception{
		if(voucherMapper.deleteByPrimaryKey((VoucherKey)voucher)>0){
			if(itMapper.deletVoucherItem(voucher.getVoucherid())>0)return true;
		}
		return false;
	}

	
	@Override
	public List<VoucherSummaryVo> listVoucherSummaryVo(VoucherKeyPlus key)
			throws Exception {
		List<VoucherSummaryVo> list = new ArrayList<VoucherSummaryVo>();
		List<Integer> subjectIdList = voucherMapper.listSubjectIdByVoucherKeyPlus(key);
		VoucherSummaryVo vo = null;
		Double credit ; Double debit ;
		for (Integer i : subjectIdList) {
			vo = new VoucherSummaryVo();
			vo.setSubjectid(i);
			vo.setSubjectname(subMapper.getSubjectNameById(i));
			credit = 0.0; 
			debit = 0.0;
			key.setSubjectid(i);
			List<VoucherSummaryIt> itList =  voucherMapper.listVoucherSummaryItem(key);
			for (VoucherSummaryIt it : itList) {
				if(it.getIsload())
					debit += it.getMoney();
				else
					credit += it.getMoney();
			}
			vo.setCreditmoney(credit);
			vo.setDebitmoney(debit);
			vo.setList(itList);
			list.add(vo);
		}
		return list;
	}

}
