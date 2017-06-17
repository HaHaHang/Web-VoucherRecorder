package com.hang.core.pojo;

import java.io.Serializable;

import com.hang.core.entity.Voucher;

public class Voucher_ItRecord implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6371766490212523161L;

	private Voucher voucher;
	
	private Voucher_It_Plus voucher_it;

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public Voucher_It_Plus getVoucher_it() {
		return voucher_it;
	}

	public void setVoucher_it(Voucher_It_Plus voucher_it) {
		this.voucher_it = voucher_it;
	}

	
}
