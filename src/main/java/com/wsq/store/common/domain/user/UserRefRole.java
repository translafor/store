package com.wsq.store.common.domain.user;

import java.util.Date;

public class UserRefRole {
    private Long FId;

    private Long FUserId;

    private Long FRoleId;

    private Date FCreateTime;

    public Long getFId() {
        return FId;
    }

    public void setFId(Long FId) {
        this.FId = FId;
    }

    public Long getFUserId() {
        return FUserId;
    }

    public void setFUserId(Long FUserId) {
        this.FUserId = FUserId;
    }

    public Long getFRoleId() {
        return FRoleId;
    }

    public void setFRoleId(Long FRoleId) {
        this.FRoleId = FRoleId;
    }

    public Date getFCreateTime() {
        return FCreateTime;
    }

    public void setFCreateTime(Date FCreateTime) {
        this.FCreateTime = FCreateTime;
    }
}