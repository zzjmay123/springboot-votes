package com.zzjmay.http;

/**
 * Created by zzjmay on 2019/2/28.
 */
public class HttpResult {

    private int code;

    private String body;

    public HttpResult() {
    }

    public HttpResult(int code, String body) {
        this.code = code;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
