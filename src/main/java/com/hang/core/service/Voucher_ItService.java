package com.hang.core.service;

import java.util.List;

import com.hang.core.entity.Voucher_It;

public interface Voucher_ItService {
	
	List<Voucher_It> getVoucher_ItByVoucherId(Short id) throws Exception;
}
