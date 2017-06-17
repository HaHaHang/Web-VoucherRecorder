package com.hang.core.service;

import java.util.List;

import com.hang.core.entity.Voucher;
import com.hang.core.entity.VoucherKey;
import com.hang.core.pojo.VoucherKeyPlus;
import com.hang.core.pojo.VoucherRecord;
import com.hang.core.pojo.VoucherSummaryVo;
import com.hang.core.pojo.Voucher_ItRecord;

public interface VoucherService {
	
	/*
	 * voucher module : init the number of the voucher
	 * show the mark of the voucher
	 */
	int getMAXValueOfItemId(VoucherKey key) throws Exception;
	
	/*
	 * voucher module : insert a voucher 
	 * 1.insert a voucher
	 * 2.select this voucher itemid
	 * 3.insert all voucheritem
	 */
	boolean insertVoucher(VoucherRecord voucherRecord) throws Exception;
	
	/*
	 * voucher module : show all accountperiod by booksetid
	 */
	List<VoucherKey> selectAllVoucherKey(Short param) throws Exception;
	
	/*
	 * Voucher Table module : show all Voucher for the accountperiod
	 * 1.select all accountperiod by booksetid
	 * 2.select all voucherhead by voucherkey
	 * 3.select and load voucheritem by voucherhead
	 */
	List<Voucher_ItRecord> selectAllVoucher(VoucherKey key) throws Exception;
	
	/*
	 * Voucher Table module : update Voucher join VoucherRecord
	 * 1.update by VoucherRecord.voucher.key
	 * 2.delete Voucher_It by VoucherRecord.voucher.voucherid
	 * 3.insert Voucher_It by VoucherRecord.voucher.voucherid
	 */
	boolean updateVoucher(VoucherRecord record) throws Exception;
	
	/*
	 * Voucher Table module : delete a item Voucher 
	 * 1.delete Voucher by Voucher.booksetid and itemid and accountperiod
	 * 2.delete Voucher_it by Voucher.voucherid 
	 */
	boolean deleteVoucher(Voucher voucher) throws Exception;
	
	/*
	 * Voucher Summary Table module : get all voucher item for voucher summary table
	 * 1.select all subjectid to list by voucherkeyplus object
	 * 2.loop list load to voucherkeyplus object and get all voucher item 
	 * 3.
	 */
	List<VoucherSummaryVo> listVoucherSummaryVo(VoucherKeyPlus key) throws Exception;



}
