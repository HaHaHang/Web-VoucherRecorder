package com.hang.core.entity;

import java.io.Serializable;

public class Voucher_It implements Serializable {
    private Short voucherid;

    private Integer subjectid;

    private Boolean isload;

    private Double money;

    private String note;

    private static final long serialVersionUID = 1L;

    public Short getVoucherid() {
        return voucherid;
    }

    public void setVoucherid(Short voucherid) {
        this.voucherid = voucherid;
    }

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

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
        this.note = note == null ? null : note.trim();
    }
}