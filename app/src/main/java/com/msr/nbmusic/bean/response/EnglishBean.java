package com.msr.nbmusic.bean.response;

import java.util.List;

/**
 * Created by Ymmmsick on 9/8/17.
 */

public class EnglishBean {

    /**
     * ret_code : 0
     * ret_message : Success
     * data : [{"english":"You have to do the best what God gave you.","chinese":"你必须尽力发挥上帝所赐予你的。"},{"english":"The mighty desert is burning for the love of a bladeof grass who shakes her head and laughs and flies away.","chinese":"无垠的沙漠热烈追求一叶绿草的爱，她摇摇头笑着飞开了。"}]
     */

    private int ret_code;
    private String ret_message;
    /**
     * english : You have to do the best what God gave you.
     * chinese : 你必须尽力发挥上帝所赐予你的。
     */

    private List<DataBean> data;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_message() {
        return ret_message;
    }

    public void setRet_message(String ret_message) {
        this.ret_message = ret_message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String english;
        private String chinese;

        public String getEnglish() {
            return english;
        }

        public void setEnglish(String english) {
            this.english = english;
        }

        public String getChinese() {
            return chinese;
        }

        public void setChinese(String chinese) {
            this.chinese = chinese;
        }
    }
}
