package com.common.demo;

import java.util.List;

public class Result<T> {
    private String message;
    private T data;
    private String Token;
    private int code;
    private String status;
    public static <T> Result<T> newSuccess() {
        Result<T> result = new Result<T>();
        result.setCode(ApiCode.Public_SUCCESSFULLY);
        result.setStatus("success");
        return result;
    }

    public static <T> Result<T> newSuccess(ApiCode code) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setStatus("success");
        return result;
    }

    public static <T> Result<T> newSuccess(T object, String token) {
        Result<T> result = new Result<T>();
        result.setData(object);
        result.setCode(ApiCode.Public_SUCCESSFULLY);
        result.setToken(token);
        result.setStatus("success");
        return result;
    }
    public static <T> Result<T> newSuccess(T object, ApiCode code, String token) {
        Result<T> result = new Result<T>();
        result.setData(object);
        result.setCode(ApiCode.Public_SUCCESSFULLY);
        result.setToken(token);
        result.setStatus("success");
        return result;
    }

    public static <T> Result<T> newSuccess(T object) {
        Result<T> result = new Result<T>();
        result.setData(object);
        result.setCode(ApiCode.Public_SUCCESSFULLY);
        result.setStatus("success");
        return result;
    }



    public static <T> Result<T> newSuccess(String token, ApiCode code) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setToken(token);
        result.setStatus("success");
        return result;
    }

    public static <T> Result<T> newFailure(ApiCode code) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setStatus("false");
        return result;
    }

    public static <T> Result<T> newFailure(String message) {
        Result<T> result = new Result<T>();
        result.setMessage(message);
        result.setCode(400);
        result.setStatus("false");
        return result;
    }

//    该参数json格式如下{"message":"账号或密码出错","data":null,"code":400,"status":"false","token":null}
    public static <T> Result<T> newFailureFromJson(String json) {
        Result<T> result = new Result<T>();
        int start = json.indexOf("message");
        int end = json.indexOf(",\"data\"");
        String message = json.substring(start + 10, end - 1);
        start=json.indexOf("\"code\":");
        end=json.indexOf(",\"status\"");
        String code=json.substring(start+7,end);
        result.setMessage(message);
        result.setCode(Integer.parseInt(code));
        result.setStatus("false");
        return result ;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setCode(ApiCode code) {
        this.code = code.getValue();
        this.message=code.getMessage();
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

}
