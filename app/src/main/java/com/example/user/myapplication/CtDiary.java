package com.example.user.myapplication;

public class CtDiary {

    /**
     * tStudent : {"f學生編號":100,"f學生姓名":"陳楷程","f學生生日":"2008-01-20T00:00:00","f學生密碼":"20080120","f學生性別":true,"fClassId":403,"f身份區分":"學生","f家庭編號":300}
     * f日誌編號 : 1
     * f學生日誌文字 : 今天天氣好冷，差點要遲到了！
     * f學生日誌照片 : null
     * f日誌批改 : 注意時間唷
     * f日期 : 2019-01-02T00:00:00
     * f學生編號 : 100
     */

    private TStudentBean tStudent;
    private int f日誌編號;
    private String f學生日誌文字;
    private Object f學生日誌照片;
    private String f日誌批改;
    private String f日期;
    private int f學生編號;

    @Override
    public String toString() {
        return "CtDiary{" +
                "tStudent=" + tStudent +
                ", f日誌編號=" + f日誌編號 +
                ", f學生日誌文字='" + f學生日誌文字 + '\'' +
                ", f學生日誌照片=" + f學生日誌照片 +
                ", f日誌批改='" + f日誌批改 + '\'' +
                ", f日期='" + f日期 + '\'' +
                ", f學生編號=" + f學生編號 +
                '}';
    }

    public CtDiary(TStudentBean tStudent, int f日誌編號, String f學生日誌文字, Object f學生日誌照片, String f日誌批改, String f日期, int f學生編號) {
        this.tStudent = tStudent;
        this.f日誌編號 = f日誌編號;
        this.f學生日誌文字 = f學生日誌文字;
        this.f學生日誌照片 = f學生日誌照片;
        this.f日誌批改 = f日誌批改;
        this.f日期 = f日期;
        this.f學生編號 = f學生編號;
    }

    public TStudentBean getTStudent() {
        return tStudent;
    }

    public void setTStudent(TStudentBean tStudent) {
        this.tStudent = tStudent;
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

    public static class TStudentBean {
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
    CtStudent student = new CtStudent();
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
