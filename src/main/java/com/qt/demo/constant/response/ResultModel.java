package com.qt.demo.constant.response;

import lombok.Data;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author Wang Zhen
 * @date 2020/7/1 9:51 上午
 */
@Data
public class ResultModel<T> implements Serializable {
    private static final long serialVersionUID = -7211292962222685696L;

    private static final int SUCCESS = 200;
    private static final int FAILURE = 254;
    private static final int UNKNOWN = 255;
    private static final int DISABLED = 240;

    @JSONField(ordinal = 1)
    private int status;

    @JSONField(ordinal = 2)
    private boolean success;

    @JSONField(ordinal = 3)
    private String message;

    @JSONField(ordinal = 4)
    private T result;

    public ResultModel<T> sendSuccessResult(T data) {
        this.status = SUCCESS;
        this.success = true;
        this.result = data;
        return this;
    }

    public ResultModel<T> sendFailedMessage(Exception e) {
        this.status = FAILURE;
        this.success = false;
        this.message = e.getMessage();
        return this;
    }

    public ResultModel<T> sendFailedMessage(String message) {
        this.status = FAILURE;
        this.success = false;
        this.message = message;
        return this;
    }

    public ResultModel<T> sendErrorMessage(int status, String errorMsg) {
        this.status = status;
        this.success = false;
        this.message = errorMsg;
        return this;
    }

    public ResultModel<T> sendErrorMessageBody(T errorMsg) {
        this.status = 1000;
        this.success = false;
        this.message = "请求错误！";
        this.result = errorMsg;
        return this;
    }
}
