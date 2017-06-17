package com.hang.core.entity;

import java.io.Serializable;

public class Subject_Add implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8204690021180056727L;

    private Integer subjectid;

    private String subjectname;

    private Byte subcatid;

    private Boolean isload;
    
    private Short userid;
    
    private String subcatname;

	public Integer getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(Integer subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public Byte getSubcatid() {
		return subcatid;
	}

	public void setSubcatid(Byte subcatid) {
		this.subcatid = subcatid;
	}

	public Boolean getIsload() {
		return isload;
	}

	public void setIsload(Boolean isload) {
		this.isload = isload;
	}

	public Short getUserid() {
		return userid;
	}

	public void setUserid(Short userid) {
		this.userid = userid;
	}

	public String getSubcatname() {
		return subcatname;
	}

	public void setSubcatname(String subcatname) {
		this.subcatname = subcatname;
	}

	
    
}
