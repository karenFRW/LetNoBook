package com.example.user.myapplication;

public class CCommunication {

    /**
     * f日期 : 2019-01-15
     * f老師交代事項 : 連假天氣會變冷，注意保暖喔！
     * f家長交代事項 : 好的，謝謝老師
     * f學生編號 : 100
     * f家長簽名 : true
     * fClassId : 403
     */

    private int f交流編號;
    private String f日期;
    private String f老師交代事項;
    private String f家長交代事項;
    private int f學生編號;
    private boolean f家長簽名;
    private int fClassId;

    @Override
    public String toString() {
        return "[{" +
                "\"f交流編號\"=" + f交流編號+ "\"" +
                ", \"f日期\"=\"" + f日期 + "\"" +
                ", \"f老師交代事項\"=\"" + f老師交代事項 + "\"" +
                ", \"f家長交代事項\"=\"" + f家長交代事項 + "\"" +
                ", \"f學生編號\"=" + f學生編號 +
                ", \"f家長簽名\"=" + f家長簽名 +
                ", \"fClassId\"=" + fClassId +
                '}';
    }

    public CCommunication(){
        super();
    }
    public CCommunication(int f交流編號, String f日期, String f老師交代事項, String f家長交代事項, int f學生編號, boolean f家長簽名, int fClassId) {
        super();
        this.f交流編號 = f交流編號;
        this.f日期 = f日期;
        this.f老師交代事項 = f老師交代事項;
        this.f家長交代事項 = f家長交代事項;
        this.f學生編號 = f學生編號;
        this.f家長簽名 = f家長簽名;
        this.fClassId = fClassId;
    }

    public int getF交流編號() {
        return f交流編號;
    }

    public void setF交流編號(int f交流編號) {
        this.f交流編號 = f交流編號;
    }

    public String getF日期() {
        return f日期;
    }

    public void setF日期(String f日期) {
        this.f日期 = f日期;
    }

    public String getF老師交代事項() {
        return f老師交代事項;
    }

    public void setF老師交代事項(String f老師交代事項) {
        this.f老師交代事項 = f老師交代事項;
    }

    public String getF家長交代事項() {
        return f家長交代事項;
    }

    public void setF家長交代事項(String f家長交代事項) {
        this.f家長交代事項 = f家長交代事項;
    }

    public int getF學生編號() {
        return f學生編號;
    }

    public void setF學生編號(int f學生編號) {
        this.f學生編號 = f學生編號;
    }

    public boolean isF家長簽名() {
        return f家長簽名;
    }

    public void setF家長簽名(boolean f家長簽名) {
        this.f家長簽名 = f家長簽名;
    }

    public int getFClassId() {
        return fClassId;
    }

    public void setFClassId(int fClassId) {
        this.fClassId = fClassId;
    }
}
