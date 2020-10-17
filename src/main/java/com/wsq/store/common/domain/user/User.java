package com.wsq.store.common.domain.user;

import java.util.Date;

public class User {
    private Long FId;

    private String FUserName;

    private String FEmail;

    private Long FHeadResId;

    private String FPhone;

    private String FIdentNumber;

    private String FIsRealAuth;

    private Date FCreateTime;

    private Date FModifyTime;

    public Long getFId() {
        return FId;
    }

    public void setFId(Long FId) {
        this.FId = FId;
    }

    public String getFUserName() {
        return FUserName;
    }

    public void setFUserName(String FUserName) {
        this.FUserName = FUserName == null ? null : FUserName.trim();
    }

    public String getFEmail() {
        return FEmail;
    }

    public void setFEmail(String FEmail) {
        this.FEmail = FEmail == null ? null : FEmail.trim();
    }

    public Long getFHeadResId() {
        return FHeadResId;
    }

    public void setFHeadResId(Long FHeadResId) {
        this.FHeadResId = FHeadResId;
    }

    public String getFPhone() {
        return FPhone;
    }

    public void setFPhone(String FPhone) {
        this.FPhone = FPhone == null ? null : FPhone.trim();
    }

    public String getFIdentNumber() {
        return FIdentNumber;
    }

    public void setFIdentNumber(String FIdentNumber) {
        this.FIdentNumber = FIdentNumber == null ? null : FIdentNumber.trim();
    }

    public String getFIsRealAuth() {
        return FIsRealAuth;
    }

    public void setFIsRealAuth(String FIsRealAuth) {
        this.FIsRealAuth = FIsRealAuth == null ? null : FIsRealAuth.trim();
    }

    public Date getFCreateTime() {
        return FCreateTime;
    }

    public void setFCreateTime(Date FCreateTime) {
        this.FCreateTime = FCreateTime;
    }

    public Date getFModifyTime() {
        return FModifyTime;
    }

    public void setFModifyTime(Date FModifyTime) {
        this.FModifyTime = FModifyTime;
    }
}