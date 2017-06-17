package com.hang.core.entity;

import java.io.Serializable;

public class Subject_Cate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6566718634079241473L;
	
    private String subcatname;

    private Byte subcatid;


	public String getSubcatname() {
		return subcatname;
	}

	public void setSubcatname(String subcatname) {
		this.subcatname = subcatname;
	}

	public Byte getSubcatid() {
		return subcatid;
	}

	public void setSubcatid(Byte subcatid) {
		this.subcatid = subcatid;
	}
    
    

}
