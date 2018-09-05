package com.demo.vertx.eventbus;

public class Message {
    private String type;
    private Object msg;

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
