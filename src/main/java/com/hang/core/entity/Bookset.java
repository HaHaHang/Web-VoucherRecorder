package com.hang.core.entity;

import java.io.Serializable;

public class Bookset implements Serializable {
    private Short booksetid;

    private Short userid;

    private String compname;

    private static final long serialVersionUID = 1L;

    public Short getBooksetid() {
        return booksetid;
    }

    public void setBooksetid(Short booksetid) {
        this.booksetid = booksetid;
    }

    public Short getUserid() {
        return userid;
    }

    public void setUserid(Short userid) {
        this.userid = userid;
    }

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname == null ? null : compname.trim();
    }

	@Override
	public String toString() {
		return "Bookset [booksetid=" + booksetid + ", userid=" + userid
				+ ", compname=" + compname + "]";
	}  
}