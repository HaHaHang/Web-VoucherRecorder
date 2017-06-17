package com.hang.core.pojo;

import java.io.Serializable;
import java.util.List;

import com.hang.core.entity.Voucher;
import com.hang.core.entity.Voucher_It;

public class VoucherRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1875964556310281262L;
	private Voucher voucher;
	private List<Voucher_It> voucher_it;
    
    
	public Voucher getVoucher() {
		return voucher;
	}
	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}
	public List<Voucher_It> getVoucher_it() {
		return voucher_it;
	}
	public void setVoucher_it(List<Voucher_It> voucher_it) {
		this.voucher_it = voucher_it;
	}
	@Override
	public String toString() {
		return "VoucherRecord [voucher=" + voucher.toString() + ", voucher_it="
				+ voucher_it.toString() + "]";
	}
	
	
}
