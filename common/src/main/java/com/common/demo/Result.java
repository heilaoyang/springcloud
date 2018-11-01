package com.common.demo;


import java.io.Serializable;
import java.util.List;

public class Result<T> implements Serializable {
    private String message;
    private T data;
    private List<T> listObject;
    private String Token;
    private int status;
    /**
     * 接口调用成功，不需要返回对象
     */
    public static <T> Result<T> newSuccess() {
        Result<T> result = new Result<T>();
        result.setStatus(ApiCode.Public_SUCCESSFULLY);
        return result;
    }
    /**
     * 接口调用成功，有返回单对象
     */
    public static <T> Result<T> newSuccess(T object) {
        Result<T> result = new Result<T>();
        result.setData(object);
        result.setStatus(ApiCode.Public_SUCCESSFULLY);
        return result;
    }
    /**
     * 接口调用成功，有返回集合对象
     */
    public static <T> Result<T> newSuccess(List<T> object) {
        Result<T> result = new Result<T>();
        result.setListObject(object);
        result.setStatus(ApiCode.Public_SUCCESSFULLY);
        return result;
    }
    /**
     * 接口调用成功，有返回对象和token
     */
    public static <T> Result<T> newSuccess_token(T object, String token) {
        Result<T> result = new Result<T>();
        result.setToken(token);
        result.setData(object);
        result.setStatus(ApiCode.Public_SUCCESSFULLY);
        return result;
    }

    /**
     * 接口调用成功，返回成功描述
     */
    public static <T> Result<T> newSuccess(ApiCode code) {
        Result<T> result = new Result<T>();
        result.setStatus(code);
        return result;
    }

    /**
     * 接口调用成功，返回成功描述和对象
     */
    public static <T> Result<T> newSuccess(T object, ApiCode code) {
        Result<T> result = new Result<T>();
        result.setStatus(code);
        result.setData(object);
        return result;
    }
    public static <T> Result<T> newSuccess(String token, ApiCode code) {
        Result<T> result = new Result<T>();
        result.setStatus(code);
        result.setToken(token);
        return result;
    }

    /**
     * 接口调用失败，有错误描述，没有返回对象
     */
    public static <T> Result<T> newFailure(ApiCode code) {
        Result<T> result = new Result<T>();
        result.setStatus(code);
        return result;
    }


    /**
     * 接口调用失败，有异常信息，没有返回对象
     */
    public static <T> Result<T> newFailure(Exception e,ApiCode code) {
        Result<T> result = new Result<T>();
        result.setMessage(e.getMessage());
        result.setStatus(code);
        return result;
    }
    public static <T> Result<T> newFailure(String errormessage) {
        Result<T> result = new Result<T>();
        result.setMessage(errormessage);
        result.setStatus(500);
        return result;
    }

    public boolean hasObject() {
        return status== 200 && data != null;
    }


    public int getCode() {
        return status;
    }

    public void setStatus(int code) {
        this.status = code;
    }
    public void setStatus(ApiCode code) {
        this.status = code.getValue();
        this.message=code.getMessage();
    }
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message){
        this.message=message;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        this.Token = token;
    }




    public List<T> getListObject() {
        return listObject;
    }

    public void setListObject(List<T> listObject) {
        this.listObject = listObject;
    }
}
