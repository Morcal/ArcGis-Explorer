package com.lyqdhgo.environment.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * Created by QiDeHong on 2017/6/5.
 */
@Entity
public class EmimsMonitorResult {
    @Id
    private Long id;
    private String monitorTaskId;
    private String monitorId;
    private String factorId;
    private String locationId;
    private String monitorType;
    private String monitorDate;
    private String timeInterval;
    private String monitorValue;
    private String overProoflag;
    private String syncFlag;
    private String standardMaxValue;
    private String standardMinValue;
    private String unit;
    private Date createDate;
    private String createUser;
    private Date updateDate;
    private String updateUser;
    private String remarks;
    @Generated(hash = 1864729653)
    public EmimsMonitorResult(Long id, String monitorTaskId, String monitorId,
            String factorId, String locationId, String monitorType,
            String monitorDate, String timeInterval, String monitorValue,
            String overProoflag, String syncFlag, String standardMaxValue,
            String standardMinValue, String unit, Date createDate,
            String createUser, Date updateDate, String updateUser, String remarks) {
        this.id = id;
        this.monitorTaskId = monitorTaskId;
        this.monitorId = monitorId;
        this.factorId = factorId;
        this.locationId = locationId;
        this.monitorType = monitorType;
        this.monitorDate = monitorDate;
        this.timeInterval = timeInterval;
        this.monitorValue = monitorValue;
        this.overProoflag = overProoflag;
        this.syncFlag = syncFlag;
        this.standardMaxValue = standardMaxValue;
        this.standardMinValue = standardMinValue;
        this.unit = unit;
        this.createDate = createDate;
        this.createUser = createUser;
        this.updateDate = updateDate;
        this.updateUser = updateUser;
        this.remarks = remarks;
    }
    @Generated(hash = 1705249974)
    public EmimsMonitorResult() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMonitorTaskId() {
        return this.monitorTaskId;
    }
    public void setMonitorTaskId(String monitorTaskId) {
        this.monitorTaskId = monitorTaskId;
    }
    public String getMonitorId() {
        return this.monitorId;
    }
    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }
    public String getFactorId() {
        return this.factorId;
    }
    public void setFactorId(String factorId) {
        this.factorId = factorId;
    }
    public String getLocationId() {
        return this.locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    public String getMonitorType() {
        return this.monitorType;
    }
    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }
    public String getMonitorDate() {
        return this.monitorDate;
    }
    public void setMonitorDate(String monitorDate) {
        this.monitorDate = monitorDate;
    }
    public String getTimeInterval() {
        return this.timeInterval;
    }
    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }
    public String getMonitorValue() {
        return this.monitorValue;
    }
    public void setMonitorValue(String monitorValue) {
        this.monitorValue = monitorValue;
    }
    public String getOverProoflag() {
        return this.overProoflag;
    }
    public void setOverProoflag(String overProoflag) {
        this.overProoflag = overProoflag;
    }
    public String getSyncFlag() {
        return this.syncFlag;
    }
    public void setSyncFlag(String syncFlag) {
        this.syncFlag = syncFlag;
    }
    public String getStandardMaxValue() {
        return this.standardMaxValue;
    }
    public void setStandardMaxValue(String standardMaxValue) {
        this.standardMaxValue = standardMaxValue;
    }
    public String getStandardMinValue() {
        return this.standardMinValue;
    }
    public void setStandardMinValue(String standardMinValue) {
        this.standardMinValue = standardMinValue;
    }
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getCreateUser() {
        return this.createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public String getUpdateUser() {
        return this.updateUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    public String getRemarks() {
        return this.remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

