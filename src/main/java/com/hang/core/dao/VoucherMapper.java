package com.hang.core.dao;

import java.util.List;

import com.hang.core.entity.Voucher;
import com.hang.core.entity.VoucherKey;
import com.hang.core.pojo.VoucherKeyPlus;
import com.hang.core.pojo.VoucherSummaryIt;

public interface VoucherMapper {
	
    int deleteByPrimaryKey(VoucherKey key);

    int insert(Voucher record);

    int insertSelective(Voucher record);

    Voucher selectByPrimaryKey(VoucherKey key);

    int updateByPrimaryKeySelective(Voucher record);

    int updateByPrimaryKey(Voucher record);
    
  //Custom
    
  	List<Voucher> listVoucherByKey(VoucherKey key) throws Exception;

  	short getVoucherIdByVoucher(Voucher voucher) throws Exception;

  	List<VoucherKey> listAccountPeriodByBooksetId(Short param) throws Exception;

  	Integer getMAXValueOfItemId(VoucherKey key) throws Exception;
  	
  	List<Integer> listSubjectIdByVoucherKeyPlus(VoucherKeyPlus key) throws Exception;
  	
  	List<VoucherSummaryIt> listVoucherSummaryItem(VoucherKeyPlus key)throws Exception;
  	
}