package com.cay.rockstock.beans;

public enum Response {

    SUCCESS(0, "success"),
    FAIL(-1, "fail");

    private final int code;
    private final String msg;

    Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
