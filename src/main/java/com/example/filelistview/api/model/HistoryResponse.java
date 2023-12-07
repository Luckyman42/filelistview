package com.example.filelistview.api.model;

import com.example.filelistview.data.History;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryResponse {
    private String Who;
    private Integer FileNum;
    private Integer MistakeNum;
    private String RequestTime;
    private String Result;


    public HistoryResponse(History history){
        this.Who = history.getWho();
        this.FileNum = history.getFileNum();
        this.MistakeNum = history.getMistakeNum();
        this.Result = history.getResult();
        Date date = new Date(history.getRequestTime());
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        this.RequestTime = format.format(date);
    }

    public String getWho() {
        return Who;
    }

    public void setWho(String who) {
        Who = who;
    }
    public String getRequestTime() {
        return RequestTime;
    }

    public void setRequestTime(String RequestTime) { this.RequestTime = RequestTime; }

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
