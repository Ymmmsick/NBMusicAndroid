package com.msr.nbmusic.api.error;

/**
 * 约定异常
 */
public class ERROR {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORD_ERROR = 1002;
    /**
     * 协议出错
     */
    public static final int HTTP_ERROR = 1003;

    /**
     * 证书出错
     */
    public static final int SSL_ERROR = 1005;

    /**
     * 连接超时
     */
    public static final int TIMEOUT_ERROR = 1006;
    /**
     * 服务器返回错误消息
     */
    public static final int SERVER_ERROR = 1007;
}