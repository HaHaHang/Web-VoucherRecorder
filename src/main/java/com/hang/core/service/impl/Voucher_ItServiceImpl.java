package com.hang.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hang.core.dao.Voucher_ItMapper;
import com.hang.core.entity.Voucher_It;
import com.hang.core.service.Voucher_ItService;
@Service
public class Voucher_ItServiceImpl implements Voucher_ItService {

	@Autowired
	Voucher_ItMapper itMapper;
	
	@Override
	public List<Voucher_It> getVoucher_ItByVoucherId(Short id) throws Exception{
		return itMapper.listVoucherItemByVoucherId(id);
	}

}
