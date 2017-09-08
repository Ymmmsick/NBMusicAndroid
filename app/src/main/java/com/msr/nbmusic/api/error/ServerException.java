package com.msr.nbmusic.api.error;

public class ServerException extends RuntimeException {
    public int code;
    public String message;
}