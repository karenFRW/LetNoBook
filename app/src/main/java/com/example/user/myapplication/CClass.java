package com.example.user.myapplication;

public class CClass {

    /**
     * fClassId : 401
     * f年級 : 1
     * f班號 : 2
     * f學級年度 : 107
     * f老師姓名 : 陳艷晴
     */

    private int fClassId;
    private String f年級;
    private String f班號;
    private String f學級年度;
    private String f老師姓名;

    public CClass() {
        super();
    }

    public CClass(int fClassId, String f年級, String f班號, String f學級年度, String f老師姓名) {
        this.fClassId = fClassId;
        this.f年級 = f年級;
        this.f班號 = f班號;
        this.f學級年度 = f學級年度;
        this.f老師姓名 = f老師姓名;
    }

    public int getFClassId() {
        return fClassId;
    }

    public void setFClassId(int fClassId) {
        this.fClassId = fClassId;
    }

    public String getF年級() {
        return f年級;
    }

    public void setF年級(String f年級) {
        this.f年級 = f年級;
    }

    public String getF班號() {
        return f班號;
    }

    public void setF班號(String f班號) {
        this.f班號 = f班號;
    }

    public String getF學級年度() {
        return f學級年度;
    }

    public void setF學級年度(String f學級年度) {
        this.f學級年度 = f學級年度;
    }

    public String getF老師姓名() {
        return f老師姓名;
    }

    public void setF老師姓名(String f老師姓名) {
        this.f老師姓名 = f老師姓名;
    }
}
