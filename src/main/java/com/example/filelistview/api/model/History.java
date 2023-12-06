package com.example.filelistview.api.model;


import java.util.Date;

public class History {
    private String Who;
    private Integer FileNum;
    private Integer MistakeNum;
    private Date When;
    private String Result;


    public String getWho() {
        return Who;
    }

    public void setWho(String who) {
        Who = who;
    }
    public Date getWhen() {
        return When;
    }

    public void setWhen(Date when) {
        When = when;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public Integer getFileNum() {
        return FileNum;
    }

    public void setFileNum(Integer fileNum) {
        FileNum = fileNum;
    }

    public Integer getMistakeNum() {
        return MistakeNum;
    }

    public void setMistakeNum(Integer mistakeNum) {
        MistakeNum = mistakeNum;
    }
}
