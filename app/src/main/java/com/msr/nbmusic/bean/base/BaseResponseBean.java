package com.msr.nbmusic.bean.base;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Ymmmsick on 5/12/17.
 */

public class BaseResponseBean<T> implements Serializable {

    private static final long serialVersionUID = 1000L;
    protected String code = "-1";//default
    protected String message;
    protected T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public boolean isSuccess() {
        return code.equals("0");
    }

    //-------------------------------------返回错误的Data---------------------------------
    /**
     * status : FAILED
     * errorData : {"code":"USER-LOGIN-PWDERROR","targetUrl":null,"message":"密码不正确.","exceptionTrace":"Trace close by server."}
     */

    private String status;
    /**
     * code : USER-LOGIN-PWDERROR
     * targetUrl : null
     * message : 密码不正确.
     * exceptionTrace : Trace close by server.
     */

    private ErrorDataBean errorData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorDataBean getErrorData() {
        return errorData;
    }

    public void setErrorData(ErrorDataBean errorData) {
        this.errorData = errorData;
    }

    public static class ErrorDataBean {
        private String code;
        private Object targetUrl;
        private String message;
        private String exceptionTrace;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Object getTargetUrl() {
            return targetUrl;
        }

        public void setTargetUrl(Object targetUrl) {
            this.targetUrl = targetUrl;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getExceptionTrace() {
            return exceptionTrace;
        }

        public void setExceptionTrace(String exceptionTrace) {
            this.exceptionTrace = exceptionTrace;
        }
    }
}
