package com.example.vuespring2.Wrapper;

public class MessageResult {
    public MessageResult(boolean success){
        this.success=success;
    }   public MessageResult(String message,int code,boolean success){
        this.success=success;
        this.message=message;
        this.code=code;
    }
    public  String message;
    public boolean success;
    public int code;
    public void  setMessage(String message){
        this.message=message;
    }
    public String getMessage(){
        return  this.message;
    }
    public void  setCode(int code){
        this.code=code;
    }
    public int getCode(){
        return  this.code;
    }
      public void  setSuccess(boolean success){
        this.success=success;
    }
    public boolean getSuccess(){
        return  this.success;
    }

}
