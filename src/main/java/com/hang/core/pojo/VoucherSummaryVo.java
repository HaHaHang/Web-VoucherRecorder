package com.hang.core.pojo;

import java.io.Serializable;
import java.util.List;

public class VoucherSummaryVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2832302450101049100L;
	
	
	
	private Integer subjectid;

    private String subjectname;
    
    private Double creditmoney;

    private Double debitmoney;
	
    private List<VoucherSummaryIt> list;

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

	public Double getCreditmoney() {
		return creditmoney;
	}

	public void setCreditmoney(Double creditmoney) {
		this.creditmoney = creditmoney;
	}

	public Double getDebitmoney() {
		return debitmoney;
	}

	public void setDebitmoney(Double debitmoney) {
		this.debitmoney = debitmoney;
	}

	public List<VoucherSummaryIt> getList() {
		return list;
	}

	public void setList(List<VoucherSummaryIt> list) {
		this.list = list;
	}
    
    
}
