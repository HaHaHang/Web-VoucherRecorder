package com.hang.core.util;

import java.util.List;

import com.hang.core.pojo.Voucher_It_Plus;

public class VoucherItPlusSortUtil {
	
	private VoucherItPlusSortUtil(){};
	
	public static List<Voucher_It_Plus> Sort(List<Voucher_It_Plus> li){
		for (Voucher_It_Plus voucher_It_Plus : li) {
			if(!voucher_It_Plus.getNote().isEmpty()){
				if(li.remove(voucher_It_Plus))
					li.add(0, voucher_It_Plus);
				return li;
			}
		}
		return li;
	}
}
