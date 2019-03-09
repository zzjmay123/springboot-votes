package com.zzjmay.forkjoin.module.vo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zzjmay on 2019/3/9.
 */
public class Context {

    public int addAns;

    public int mulAns;

    public String concatAns;

    public Map<String,Object> ans = new ConcurrentHashMap<>();

    public Context() {
    }

    public int getAddAns() {
        return addAns;
    }

    public void setAddAns(int addAns) {
        this.addAns = addAns;
    }

    public int getMulAns() {
        return mulAns;
    }

    public void setMulAns(int mulAns) {
        this.mulAns = mulAns;
    }

    public String getConcatAns() {
        return concatAns;
    }

    public void setConcatAns(String concatAns) {
        this.concatAns = concatAns;
    }

    public Map<String, Object> getAns() {
        return ans;
    }

    public void setAns(Map<String, Object> ans) {
        this.ans = ans;
    }

    @Override
    public String toString() {
        return "Context{" +
                "addAns=" + addAns +
                ", mulAns=" + mulAns +
                ", concatAns='" + concatAns + '\'' +
                ", ans=" + ans +
                '}';
    }
}
