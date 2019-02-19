package com.example.user.myapplication;

public class CLocation {
    /**
     * fGPS編號 : 1
     * f姓名 : 陳楷程
     * f經度 : 120.932232
     * f緯度 : 24.526456
     * f時間 : 2019-01-30 11:04:25
     * fClassID : 403
     * f家庭編號 : 300
     */

    private int fGPS編號;
    private String f姓名;
    private String f經度;
    private String f緯度;
    private String f時間;
    private int fClassID;
    private int f家庭編號;

    @Override
    public String toString() {
        return "[{" +
                "\"fGPS編號\":\'" + fGPS編號 +"\'"+
                ", \"f姓名\":\"" + f姓名 + "\"" +
                ", \"f經度\":\"" + f經度 + "\"" +
                ", \"f緯度\":\"" + f緯度 + "\"" +
                ", \"f時間\":\"" + f時間 + "\"" +
                ", \"fClassID\":\'" + fClassID +"\'"+
                ", \"f家庭編號\":\'" + f家庭編號+"\'" +
                "}]";
    }

    public CLocation(){
        super();
    }
    public CLocation(String f姓名, String f經度, String f緯度, String f時間, int fClassID, int f家庭編號) {
        this.f姓名 = f姓名;
        this.f經度 = f經度;
        this.f緯度 = f緯度;
        this.f時間 = f時間;
        this.fClassID = fClassID;
        this.f家庭編號 = f家庭編號;
    }

    public int getFGPS編號() {
        return fGPS編號;
    }

    public void setFGPS編號(int fGPS編號) {
        this.fGPS編號 = fGPS編號;
    }

    public String getF姓名() {
        return f姓名;
    }

    public void setF姓名(String f姓名) {
        this.f姓名 = f姓名;
    }

    public String getF經度() {
        return f經度;
    }

    public void setF經度(String f經度) {
        this.f經度 = f經度;
    }

    public String getF緯度() {
        return f緯度;
    }

    public void setF緯度(String f緯度) {
        this.f緯度 = f緯度;
    }

    public String getF時間() {
        return f時間;
    }

    public void setF時間(String f時間) {
        this.f時間 = f時間;
    }

    public int getFClassID() {
        return fClassID;
    }

    public void setFClassID(int fClassID) {
        this.fClassID = fClassID;
    }

    public int getF家庭編號() {
        return f家庭編號;
    }

    public void setF家庭編號(int f家庭編號) {
        this.f家庭編號 = f家庭編號;
    }
}
