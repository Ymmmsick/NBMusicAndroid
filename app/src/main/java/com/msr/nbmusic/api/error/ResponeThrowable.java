package com.msr.nbmusic.api.error;

public class ResponeThrowable extends Exception {
    public int code;
    public String message;

    public ResponeThrowable(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    /**
     * 获取统一修改过的Message
     *
     * @return
     */
    public String getLMessage() {
        return message;
    }
}