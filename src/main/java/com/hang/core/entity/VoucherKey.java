package com.hang.core.entity;

import java.io.Serializable;

public class VoucherKey implements Serializable {
    private Short booksetid;

    private Byte itemid;

    private String accountperiod;

    private static final long serialVersionUID = 1L;

    public Short getBooksetid() {
        return booksetid;
    }

    public void setBooksetid(Short booksetid) {
        this.booksetid = booksetid;
    }

    public Byte getItemid() {
        return itemid;
    }

    public void setItemid(Byte itemid) {
        this.itemid = itemid;
    }

    public String getAccountperiod() {
        return accountperiod;
    }

    public void setAccountperiod(String accountperiod) {
        this.accountperiod = accountperiod == null ? null : accountperiod.trim();
    }
}