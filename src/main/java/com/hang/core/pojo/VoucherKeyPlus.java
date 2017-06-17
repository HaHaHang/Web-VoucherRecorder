package com.hang.core.pojo;

import com.hang.core.entity.VoucherKey;

public class VoucherKeyPlus extends VoucherKey{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -2586605513198544279L;
	private Integer subjectid;

	public Integer getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(Integer subjectid) {
		this.subjectid = subjectid;
	}
    
    public void setVoucherKey(VoucherKey key){
    	this.setAccountperiod(key.getAccountperiod());
    	this.setBooksetid(key.getBooksetid());
    }
}
