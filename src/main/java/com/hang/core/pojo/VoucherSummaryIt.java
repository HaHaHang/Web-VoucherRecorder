package com.hang.core.pojo;

import java.io.Serializable;
import java.util.Date;

public class VoucherSummaryIt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5030823215753492159L;
	
    private Boolean isload;

    private Double money;

    private String note;
    
    private Date date;
    
    private Byte itemid;

	public Boolean getIsload() {
		return isload;
	}

	public void setIsload(Boolean isload) {
		this.isload = isload;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Byte getItemid() {
		return itemid;
	}

	public void setItemid(Byte itemid) {
		this.itemid = itemid;
	}
    
    

}
