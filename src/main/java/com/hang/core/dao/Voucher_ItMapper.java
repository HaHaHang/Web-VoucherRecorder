package com.hang.core.dao;

import java.util.List;

import com.hang.core.entity.Voucher;
import com.hang.core.entity.Voucher_It;
import com.hang.core.pojo.Voucher_It_Plus;

public interface Voucher_ItMapper {
    int insert(Voucher_It record) throws Exception;

    int insertSelective(Voucher_It record) throws Exception;
    
    int deletVoucherItem(Short id) throws Exception;
	
	List<Voucher_It_Plus> listVoucherItPlusByVoucherId(Voucher li) throws Exception;
	
	List<Voucher_It> listVoucherItemByVoucherId(Short id) throws Exception;

}