package com.hang.core.entity;

import java.io.Serializable;
import java.util.Date;

public class Voucher extends VoucherKey implements Serializable {
    private Short voucherid;

    private Date date;

    private Byte docNum;

    private static final long serialVersionUID = 1L;

    public Short getVoucherid() {
        return voucherid;
    }

    public void setVoucherid(Short voucherid) {
        this.voucherid = voucherid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Byte getDocNum() {
        return docNum;
    }

    public void setDocNum(Byte docNum) {
        this.docNum = docNum;
    }
}