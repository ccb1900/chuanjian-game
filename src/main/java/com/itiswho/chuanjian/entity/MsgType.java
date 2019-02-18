package com.itiswho.chuanjian.entity;

public class MsgType {
    private String type;
    private SingleMessage content;

    public SingleMessage getContent() {
        return content;
    }

    public void setContent(SingleMessage content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
