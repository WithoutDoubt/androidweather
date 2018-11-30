package com.example.admin.androidweather.db;

import org.litepal.crud.DataSupport;

public class TransferPlan extends DataSupport {
    /**
     * 1.计划单id
     * 2.计划单编码
     * 3.计划单名称
     * 4.计划单计划日期
     * 5.计划单审核状态
     * 6.计划单倒运组
     * 7.计划单审核领导
     * 8.计划单备注
     * 9.生产线号
     * */
    private String id;
    private String code;
    private String planName;
    private String planDate;
    private int isReject;
    private String transferTeam;
    private String leaderName;
    private String remarks;
    private String lineId;

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public int getIsReject() {
        return isReject;
    }

    public void setIsReject(int isReject) {
        this.isReject = isReject;
    }

    public String getTransferTeam() {
        return transferTeam;
    }

    public void setTransferTeam(String transferTeam) {
        this.transferTeam = transferTeam;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }




}
