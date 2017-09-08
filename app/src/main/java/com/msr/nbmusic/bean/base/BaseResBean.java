package com.msr.nbmusic.bean.base;

import java.io.Serializable;

/**
 * Created by Ymmmsick on 5/12/17.
 */

public class BaseResBean<T> implements Serializable {

    private static final long serialVersionUID = 1000L;
    /**
     * ret_code : 0
     * ret_message : Success
     * data : [{"english":"You have to do the best what God gave you.","chinese":"你必须尽力发挥上帝所赐予你的。"},{"english":"The mighty desert is burning for the love of a bladeof grass who shakes her head and laughs and flies away.","chinese":"无垠的沙漠热烈追求一叶绿草的爱，她摇摇头笑着飞开了。"}]
     */

    private int showapi_res_code;
    private String showapi_res_error;
    protected T showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public T getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(T showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public boolean isSuccess() {
        return showapi_res_code == 0;
    }
}
