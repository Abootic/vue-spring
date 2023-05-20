package com.example.vuespring2.Wrapper.Impl;

import com.example.vuespring2.Wrapper.Interface.ResultGeneric;
import com.example.vuespring2.Wrapper.MessageResult;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
public class ResultGenericImpl<T> implements ResultGeneric<T> {

    private MessageResult status;
    private T Data;

    @Override
    public void setStatus(MessageResult status) {
        this.status = status;
    }

    @Override
    public MessageResult getStatus() {
        return this.status;
    }

    @Override
    public T getData() {
        return this.Data;
    }

    public static <T> ResultGeneric fail() {
        var resultGenericImpl = new ResultGenericImpl<T>();
        resultGenericImpl.status = new MessageResult(false);
        return resultGenericImpl;
    }

    public static <T> ResultGeneric fail(String message, int code) {
        var resultGenericImpl = new ResultGenericImpl<T>();
        resultGenericImpl.status = new MessageResult(message, code, false);
        return resultGenericImpl;
    }

    public static <T> CompletableFuture<ResultGeneric> FailAsync(String message, int code) {

        return CompletableFuture.supplyAsync(() -> fail(message, 500));
    }

    public static <T> ResultGeneric success() {
        var resultGenericImpl = new ResultGenericImpl<T>();
        resultGenericImpl.status = new MessageResult(true);
        return resultGenericImpl;
    } public static <T> ResultGeneric success(T Data) {
        var resultGenericImpl = new ResultGenericImpl<T>();
        resultGenericImpl.status = new MessageResult(true);
        resultGenericImpl.Data=Data;
        return resultGenericImpl;
    }

    public static <T> ResultGeneric success(String message, int code) {

        var resultGenericImpl = new ResultGenericImpl<T>();
        resultGenericImpl.status = new MessageResult(message, code, true);
        return resultGenericImpl;
    }

    public static <T> ResultGeneric success(T Data, String message, int code) {

        var resultGenericImpl = new ResultGenericImpl<T>();
        resultGenericImpl.status = new MessageResult( message, code, true);
        resultGenericImpl.Data = Data;
        return resultGenericImpl;
    }

    public static <T> CompletableFuture<ResultGeneric> successAsync() {
        return CompletableFuture.supplyAsync(() -> success());
    }
    public static <T> CompletableFuture<ResultGeneric> successAsync(String message,int code) {
        return CompletableFuture.supplyAsync(() -> success(message,200));
    }

    public static <T> Future<ResultGeneric> successAsync(T Data, String message, int code) {
        return  AsyncResult.forValue( success(Data,message,200));
      //  return CompletableFuture.supplyAsync(() -> success(Data,message,200));
    }
    public static <T> CompletableFuture<ResultGeneric> successAsync(T Data) {
        return CompletableFuture.supplyAsync(() -> success(Data));
    }
}