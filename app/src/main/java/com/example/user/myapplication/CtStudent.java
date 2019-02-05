package com.example.user.myapplication;

public class CtStudent {

    /**
     * f學生編號 : 100
     * f學生姓名 : 陳楷程
     * f學生生日 : 2008-01-20T00:00:00
     * f學生密碼 : 20080120
     * f學生性別 : true
     * fClassId : 403
     * f身份區分 : 學生
     * f家庭編號 : 300
     */

    private int f學生編號;
    private String f學生姓名;
    private String f學生生日;
    private String f學生密碼;
    private boolean f學生性別;
    private int fClassId;
    private String f身份區分;
    private int f家庭編號;

    public  CtStudent(){
        super();
    }

    public  CtStudent(int f學生編號, String f學生姓名, int fClassId){
        this.f學生編號 = f學生編號;
        this.f學生姓名 = f學生姓名;
        this.fClassId = fClassId;
    }
    public CtStudent(int f學生編號, String f學生姓名, String f學生生日, String f學生密碼, boolean f學生性別, int fClassId, String f身份區分, int f家庭編號) {
        super();
        this.f學生編號 = f學生編號;
        this.f學生姓名 = f學生姓名;
        this.f學生生日 = f學生生日;
        this.f學生密碼 = f學生密碼;
        this.f學生性別 = f學生性別;
        this.fClassId = fClassId;
        this.f身份區分 = f身份區分;
        this.f家庭編號 = f家庭編號;
    }

    public int getF學生編號() {
        return f學生編號;
    }

    public void setF學生編號(int f學生編號) {
        this.f學生編號 = f學生編號;
    }

    public String getF學生姓名() {
        return f學生姓名;
    }

    public void setF學生姓名(String f學生姓名) {
        this.f學生姓名 = f學生姓名;
    }

    public String getF學生生日() {
        return f學生生日;
    }

    public void setF學生生日(String f學生生日) {
        this.f學生生日 = f學生生日;
    }

    public String getF學生密碼() {
        return f學生密碼;
    }

    public void setF學生密碼(String f學生密碼) {
        this.f學生密碼 = f學生密碼;
    }

    public boolean isF學生性別() {
        return f學生性別;
    }

    public void setF學生性別(boolean f學生性別) {
        this.f學生性別 = f學生性別;
    }

    public int getFClassId() {
        return fClassId;
    }

    public void setFClassId(int fClassId) {
        this.fClassId = fClassId;
    }

    public String getF身份區分() {
        return f身份區分;
    }

    public void setF身份區分(String f身份區分) {
        this.f身份區分 = f身份區分;
    }

    public int getF家庭編號() {
        return f家庭編號;
    }

    public void setF家庭編號(int f家庭編號) {
        this.f家庭編號 = f家庭編號;
    }
}
