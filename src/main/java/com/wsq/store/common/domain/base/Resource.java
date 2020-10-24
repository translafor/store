package com.wsq.store.common.domain.base;

import java.util.Date;

public class Resource {
    private Long FId;

    private String FFileName;

    private Integer FLength;

    private String FFilePath;

    private Date FCreateTime;

    public Long getFId() {
        return FId;
    }

    public void setFId(Long FId) {
        this.FId = FId;
    }

    public String getFFileName() {
        return FFileName;
    }

    public void setFFileName(String FFileName) {
        this.FFileName = FFileName == null ? null : FFileName.trim();
    }

    public Integer getFLength() {
        return FLength;
    }

    public void setFLength(Integer FLength) {
        this.FLength = FLength;
    }

    public String getFFilePath() {
        return FFilePath;
    }

    public void setFFilePath(String FFilePath) {
        this.FFilePath = FFilePath == null ? null : FFilePath.trim();
    }

    public Date getFCreateTime() {
        return FCreateTime;
    }

    public void setFCreateTime(Date FCreateTime) {
        this.FCreateTime = FCreateTime;
    }
}