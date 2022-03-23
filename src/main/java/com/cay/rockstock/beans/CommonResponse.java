package com.cay.rockstock.beans;

import lombok.Data;

@Data
public class CommonResponse {

    private boolean success = true;

    private int code;

    private String msg;

    public static CommonResponse success() {
        return of(Response.SUCCESS);
    }

    public static CommonResponse fail() {
        return of(Response.FAIL);
    }

    public static CommonResponse of(Response response) {
        return of(response.getCode(), response.getMsg());
    }

    public static CommonResponse of(int code) {
        CommonResponse response = new CommonResponse();
        response.setCode(code);
        response.setSuccess(code == 0);
        return response;
    }

    public static CommonResponse of(int code, String msg) {
        CommonResponse response = new CommonResponse();
        response.setCode(code);
        response.setSuccess(code == 0);
        response.setMsg(msg);
        return response;
    }
}
