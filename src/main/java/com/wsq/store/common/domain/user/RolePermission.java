package com.wsq.store.common.domain.user;

import java.util.Date;

public class RolePermission {
    private Long FId;

    private Long FRoleId;

    private String FPermission;

    private Date FCreateTime;

    public Long getFId() {
        return FId;
    }

    public void setFId(Long FId) {
        this.FId = FId;
    }

    public Long getFRoleId() {
        return FRoleId;
    }

    public void setFRoleId(Long FRoleId) {
        this.FRoleId = FRoleId;
    }

    public String getFPermission() {
        return FPermission;
    }

    public void setFPermission(String FPermission) {
        this.FPermission = FPermission == null ? null : FPermission.trim();
    }

    public Date getFCreateTime() {
        return FCreateTime;
    }

    public void setFCreateTime(Date FCreateTime) {
        this.FCreateTime = FCreateTime;
    }
}