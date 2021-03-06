package com.example.user.myapplication;

public class CInfo {

    /**
     * fInfoId : 1
     * f日期 : 2019-01-07T00:00:00
     * f作業通知 : 注音寫一遍
     * f用品通知 : 剪刀、色紙
     * f其他通知 : null
     * fClassId : 403
     * f老師編號 : 200
     */


    private int fInfoId;
    private String f日期;
    private String f科目;
    private String f作業通知;
    private String f用品通知;
    private String f其他通知;
    private int fClassId;
    private int f老師編號;

    @Override
    public String toString() {
        return "[{" +
                "\"f日期\":\"" + f日期 + "\"" +
                ", \"f科目\":\"" + f科目 + "\"" +
                ", \"f作業通知\":\"" + f作業通知 + "\"" +
                ", \"f用品通知\":\"" + f用品通知 + "\"" +
                ", \"f其他通知\":\"" + f其他通知 + "\"" +
                ", \"fClassId\":" + fClassId +
                ", \"f老師編號\":" + f老師編號 +
                "}]";
    }

    public CInfo(){
        super();
    }
    public CInfo(String f日期, String f科目, String f作業通知, String f用品通知, String f其他通知, int fClassId, int f老師編號) {
        this.fInfoId = fInfoId;
        this.f日期 = f日期;
        this.f科目 = f科目;
        this.f作業通知 = f作業通知;
        this.f用品通知 = f用品通知;
        this.f其他通知 = f其他通知;
        this.fClassId = fClassId;
        this.f老師編號 = f老師編號;
    }
    public CInfo(int fInfoId, String f日期, String f科目, String f作業通知, String f用品通知, String f其他通知, int fClassId, int f老師編號) {
        this.fInfoId = fInfoId;
        this.f日期 = f日期;
        this.f科目 = f科目;
        this.f作業通知 = f作業通知;
        this.f用品通知 = f用品通知;
        this.f其他通知 = f其他通知;
        this.fClassId = fClassId;
        this.f老師編號 = f老師編號;
    }

    public int getfInfoId() {
        return fInfoId;
    }

    public void setfInfoId(int fInfoId) {
        this.fInfoId = fInfoId;
    }

    public String getF日期() {
        return f日期;
    }

    public void setF日期(String f日期) {
        this.f日期 = f日期;
    }

    public String getF科目() {
        return f科目;
    }

    public void setF科目(String f科目) {
        this.f科目 = f科目;
    }

    public String getF作業通知() {
        return f作業通知;
    }

    public void setF作業通知(String f作業通知) {
        this.f作業通知 = f作業通知;
    }

    public String getF用品通知() {
        return f用品通知;
    }

    public void setF用品通知(String f用品通知) {
        this.f用品通知 = f用品通知;
    }

    public String getF其他通知() {
        return f其他通知;
    }

    public void setF其他通知(String f其他通知) {
        this.f其他通知 = f其他通知;
    }

    public int getFClassId() {
        return fClassId;
    }

    public void setFClassId(int fClassId) {
        this.fClassId = fClassId;
    }

    public int getF老師編號() {
        return f老師編號;
    }

    public void setF老師編號(int f老師編號) {
        this.f老師編號 = f老師編號;
    }
}
