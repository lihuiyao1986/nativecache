package com.huasco.greendao.utils;

import java.io.Serializable;

/**
 * Created by jk on 2017/8/13.
 */
public class DaoRespModel<T> implements Serializable {

    private static final long serialVersionUID = 6113883463675528032L;

    // 数据
    private T result;

    // 是否成功
    private boolean success = true;

    // 描述
    private String message = "响应成功";

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
