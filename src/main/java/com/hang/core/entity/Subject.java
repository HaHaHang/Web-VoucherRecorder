package com.hang.core.entity;

import java.io.Serializable;

public class Subject implements Serializable {
	
    private Integer subjectid;

    private String subjectname;

    private Byte subcatid;

    private Boolean isload;

    private static final long serialVersionUID = 1L;

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
        this.subjectname = subjectname == null ? null : subjectname.trim();
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
}