package com.common.demo;

import java.util.List;

public class Result<T> {
    private String message;
    private T data;
    private List<T> listObject;
    private String Token;
    private int status;

    public static <T> Result<T> newSuccess() {
        Result<T> result = new Result<T>();
        result.setStatus(ApiCode.Public_SUCCESSFULLY);
        return result;
    }

    public static <T> Result<T> newSuccess(ApiCode code) {
        Result<T> result = new Result<T>();
        result.setStatus(code);
        return result;
    }

    public static <T> Result<T> newSuccess(T object, String token) {
        Result<T> result = new Result<T>();
        result.setData(object);
        result.setStatus(ApiCode.Public_SUCCESSFULLY);
        result.setToken(token);
        return result;
    }
    public static <T> Result<T> newSuccess(T object, ApiCode code, String token) {
        Result<T> result = new Result<T>();
        result.setData(object);
        result.setStatus(ApiCode.Public_SUCCESSFULLY);
        result.setToken(token);
        result.setStatus(code);
        return result;
    }

    public static <T> Result<T> newSuccess(T object) {
        Result<T> result = new Result<T>();
        result.setData(object);
        result.setStatus(ApiCode.Public_SUCCESSFULLY);
        return result;
    }

    public static <T> Result<T> newSuccess(List<T> object) {
        Result<T> result = new Result<T>();
        result.setListObject(object);
        result.setStatus(ApiCode.Public_SUCCESSFULLY);
        return result;
    }
    public static <T> Result<T> newSuccess(List<T> object, String token) {
        Result<T> result = new Result<T>();
        result.setListObject(object);
        result.setStatus(ApiCode.Public_SUCCESSFULLY);
        result.setToken(token);
        return result;
    }

    public static <T> Result<T> newSuccess(String token, ApiCode code) {
        Result<T> result = new Result<T>();
        result.setStatus(code);
        result.setToken(token);
        return result;
    }

    public static <T> Result<T> newFailure(ApiCode code) {
        Result<T> result = new Result<T>();
        result.setStatus(code);
        return result;
    }

    public static <T> Result<T> newFailure(String message) {
        Result<T> result = new Result<T>();
        result.setMessage(message);
        result.setStatus(400);
        return result;
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

    public List<T> getListObject() {
        return listObject;
    }

    public void setListObject(List<T> listObject) {
        this.listObject = listObject;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public void setStatus(ApiCode status) {
        this.status = status.getValue();
        this.message=status.getMessage();
    }



}
