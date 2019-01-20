package com.zzjmay.common;

import java.io.Serializable;

/**
 * Created by zzjmay on 2019/1/20.
 */
public class BaseResult implements Serializable {

    private boolean success;

    private String code;

    private String info;

    public BaseResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
