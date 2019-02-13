package com.example.user.myapplication;

public class CDiary {


    /**
     * f日誌編號 : 1
     * f學生日誌文字 : 今天天氣好冷，差點要遲到了！
     * f學生日誌照片 : null
     * f日誌批改 : 注意時間唷
     * f日期 : 2019-01-02T00:00:00
     * f學生編號 : 100
     */

    private int f日誌編號;
    private String f學生日誌文字;
    private Object f學生日誌照片;
    private String f日誌批改;
    private String f日期;
    private int f學生編號;

    public CDiary(String f學生日誌文字, String f日誌批改, String f日期, int f學生編號) {
        this.f學生日誌文字 = f學生日誌文字;
        this.f日誌批改 = f日誌批改;
        this.f日期 = f日期;
        this.f學生編號 = f學生編號;
    }

    public int getF日誌編號() {
        return f日誌編號;
    }

    public void setF日誌編號(int f日誌編號) {
        this.f日誌編號 = f日誌編號;
    }

    public String getF學生日誌文字() {
        return f學生日誌文字;
    }

    public void setF學生日誌文字(String f學生日誌文字) {
        this.f學生日誌文字 = f學生日誌文字;
    }

    public Object getF學生日誌照片() {
        return f學生日誌照片;
    }

    public void setF學生日誌照片(Object f學生日誌照片) {
        this.f學生日誌照片 = f學生日誌照片;
    }

    public String getF日誌批改() {
        return f日誌批改;
    }

    public void setF日誌批改(String f日誌批改) {
        this.f日誌批改 = f日誌批改;
    }

    public String getF日期() {
        return f日期;
    }

    public void setF日期(String f日期) {
        this.f日期 = f日期;
    }

    public int getF學生編號() {
        return f學生編號;
    }

    public void setF學生編號(int f學生編號) {
        this.f學生編號 = f學生編號;
    }
}
