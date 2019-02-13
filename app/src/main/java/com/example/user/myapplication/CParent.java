package com.example.user.myapplication;

import java.util.List;

public class CParent {

    /**
     * tStudents : [{"f學生編號":100,"f學生姓名":"陳楷程","f學生生日":"2008-01-20T00:00:00","f學生密碼":"20080120","f學生性別":true,"fClassId":403,"f身份區分":"學生","f家庭編號":300}]
     * f家庭編號 : 300
     * f家長姓名 : 陳美伶
     * f家長生日 : 1980-05-06T00:00:00
     * f家長密碼 : 19800506
     * f身份區分 : 家長
     */

    private int f家庭編號;
    private String f家長姓名;
    private String f家長生日;
    private String f家長密碼;
    private String f身份區分;
    private List<TStudentsBean> tStudents;


    public CParent(){
        super();
    }

    public CParent(int f家庭編號, String f家長姓名, String f家長生日, String f家長密碼, String f身份區分, List<TStudentsBean> tStudents) {
        super();
        this.f家庭編號 = f家庭編號;
        this.f家長姓名 = f家長姓名;
        this.f家長生日 = f家長生日;
        this.f家長密碼 = f家長密碼;
        this.f身份區分 = f身份區分;
        this.tStudents = tStudents;
    }

    public int getF家庭編號() {
        return f家庭編號;
    }

    public void setF家庭編號(int f家庭編號) {
        this.f家庭編號 = f家庭編號;
    }

    public String getF家長姓名() {
        return f家長姓名;
    }

    public void setF家長姓名(String f家長姓名) {
        this.f家長姓名 = f家長姓名;
    }

    public String getF家長生日() {
        return f家長生日;
    }

    public void setF家長生日(String f家長生日) {
        this.f家長生日 = f家長生日;
    }

    public String getF家長密碼() {
        return f家長密碼;
    }

    public void setF家長密碼(String f家長密碼) {
        this.f家長密碼 = f家長密碼;
    }

    public String getF身份區分() {
        return f身份區分;
    }

    public void setF身份區分(String f身份區分) {
        this.f身份區分 = f身份區分;
    }

    public List<TStudentsBean> getTStudents() {
        return tStudents;
    }

    public void setTStudents(List<TStudentsBean> tStudents) {
        this.tStudents = tStudents;
    }

    public static class TStudentsBean {
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
}
