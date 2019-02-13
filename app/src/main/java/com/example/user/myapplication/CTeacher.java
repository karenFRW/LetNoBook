package com.example.user.myapplication;

public class CTeacher {
    /**
     * f老師編號 : 200
     * f老師姓名 : 李旻峻
     * f老師生日 : 1980-05-22T00:00:00
     * f老師密碼 : 19800522
     * f老師性別 : true
     * f身份區分 : 教師
     */

    private int f老師編號;
    private String f老師姓名;
    private String f老師生日;
    private String f老師密碼;
    private boolean f老師性別;
    private String f身份區分;

    public CTeacher(){
        super();
    }


    public CTeacher(int f老師編號, String f老師姓名, String f老師密碼){
        this.f老師編號 = f老師編號;
        this.f老師姓名 = f老師姓名;
        this.f老師密碼 = f老師密碼;
    }

    public CTeacher(int f老師編號, String f老師姓名, String f老師生日, String f老師密碼, boolean f老師性別, String f身份區分) {
        super();
        this.f老師編號 = f老師編號;
        this.f老師姓名 = f老師姓名;
        this.f老師生日 = f老師生日;
        this.f老師密碼 = f老師密碼;
        this.f老師性別 = f老師性別;
        this.f身份區分 = f身份區分;
    }

    public int getf老師編號() {
        return f老師編號;
    }

    public void setf老師編號(int f老師編號) {
        this.f老師編號 = f老師編號;
    }

    public String getf老師姓名() {
        return f老師姓名;
    }

    public void setf老師姓名(String f老師姓名) {
        this.f老師姓名 = f老師姓名;
    }

    public String getf老師生日() {
        return f老師生日;
    }

    public void setf老師生日(String f老師生日) {
        this.f老師生日 = f老師生日;
    }

    public String getf老師密碼() {
        return f老師密碼;
    }

    public void setf老師密碼(String f老師密碼) {
        this.f老師密碼 = f老師密碼;
    }

    public boolean isf老師性別() {
        return f老師性別;
    }

    public void setf老師性別(boolean f老師性別) {
        this.f老師性別 = f老師性別;
    }

    public String getf身份區分() {
        return f身份區分;
    }

    public void setf身份區分(String f身份區分) {
        this.f身份區分 = f身份區分;
    }
}
