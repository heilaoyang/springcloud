package com.common.demo;


import java.io.Serializable;
import java.util.List;

public class Result<T> implements Serializable {
    private int code;
    private String message;
    private T object;
    private List<T> listObject;
    private Exception exception;
    private String Token;
    /**
     * 接口调用成功，不需要返回对象
     */
    public static <T> Result<T> newSuccess() {
        Result<T> result = new Result<T>();
        result.setCode(ApiCode.Public_SUCCESSFULLY);
        return result;
    }
    /**
     * 接口调用成功，有返回单对象
     */
    public static <T> Result<T> newSuccess(T object) {
        Result<T> result = new Result<T>();
        result.setObject(object);
        result.setCode(ApiCode.Public_SUCCESSFULLY);
        return result;
    }
    /**
     * 接口调用成功，有返回集合对象
     */
    public static <T> Result<T> newSuccess(List<T> object) {
        Result<T> result = new Result<T>();
        result.setListObject(object);
        result.setCode(ApiCode.Public_SUCCESSFULLY);
        return result;
    }
    /**
     * 接口调用成功，有返回对象和token
     */
    public static <T> Result<T> newSuccess_token(T object, String token) {
        Result<T> result = new Result<T>();
        result.setToken(token);
        result.setObject(object);
        result.setCode(ApiCode.Public_SUCCESSFULLY);
        return result;
    }

    /**
     * 接口调用成功，返回成功描述
     */
    public static <T> Result<T> newSuccess(ApiCode code) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        return result;
    }

    /**
     * 接口调用成功，返回成功描述和对象
     */
    public static <T> Result<T> newSuccess(T object, ApiCode code) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setObject(object);
        return result;
    }

    /**
     * 接口调用失败，有错误描述，没有返回对象
     */
    public static <T> Result<T> newFailure(ApiCode code) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        return result;
    }


    /**
     * 接口调用失败，有异常信息，没有返回对象
     */
    public static <T> Result<T> newFailure(Exception e,ApiCode code) {
        Result<T> result = new Result<T>();
        result.setException(e);
        result.setCode(code);
        return result;
    }

    public boolean hasObject() {
        return code == 0 && object != null;
    }

    public boolean hasException() {
        return exception != null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(ApiCode code) {
        this.code = code.getValue();
        this.message=code.getMessage();
    }
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message){
        this.message=message;
    }


    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
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
