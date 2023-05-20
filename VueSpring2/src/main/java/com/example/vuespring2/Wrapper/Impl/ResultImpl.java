package com.example.vuespring2.Wrapper.Impl;

import com.example.vuespring2.Wrapper.Interface.Result;
import com.example.vuespring2.Wrapper.MessageResult;

import java.util.concurrent.CompletableFuture;

public class ResultImpl  implements Result {


public MessageResult status;
    @Override
    public void setStatus(MessageResult status) {
        this.status=status;
    }

    @Override
    public MessageResult getStatus() {
        return this.status;
    }
    public static Result Fail() {
     var resultImpl = new ResultImpl();
        resultImpl.status=new MessageResult(false);
        return resultImpl;
    }
    public static Result Fail(String message,int code) {
     var resultImpl = new ResultImpl();
        resultImpl.status=new MessageResult(message,code,false);
        return resultImpl;
    }

    public static CompletableFuture<Result> FailAsync(String message, int code) {

        return CompletableFuture.supplyAsync(()->Fail(message,500));
    }
    public static Result success() {
        var resultImpl = new ResultImpl();
        resultImpl.status=new MessageResult(true);
        return resultImpl;
    }
    public static CompletableFuture<Result> successAsync() {

        return CompletableFuture.supplyAsync(()->success());
    }
    public static Result success(String message,int code) {

        var resultImpl = new ResultImpl();
        resultImpl.status=new MessageResult(message,code,true);
        return resultImpl;
    }
    public static CompletableFuture<Result> successAsync(String message,int code) {

        return CompletableFuture.supplyAsync(()->success(message,200));
    }

}
