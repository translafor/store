package com.wsq.store.common.domain.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long FId;

    private String FUserName;

    private String FEmail;

    private Long FHeadResId;

    private String FPhone;

    private String FPassword;

    private String FIdentNumber;

    private String FIsRealAuth;

    private Date FCreateTime;

    private Date FModifyTime;


}