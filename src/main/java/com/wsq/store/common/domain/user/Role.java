package com.wsq.store.common.domain.user;

import java.util.Date;

public class Role {
    private Long FId;

    private String FRoleNumber;

    private Date FCreateTime;

    public Long getFId() {
        return FId;
    }

    public void setFId(Long FId) {
        this.FId = FId;
    }

    public String getFRoleNumber() {
        return FRoleNumber;
    }

    public void setFRoleNumber(String FRoleNumber) {
        this.FRoleNumber = FRoleNumber == null ? null : FRoleNumber.trim();
    }

    public Date getFCreateTime() {
        return FCreateTime;
    }

    public void setFCreateTime(Date FCreateTime) {
        this.FCreateTime = FCreateTime;
    }
}