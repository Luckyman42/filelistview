package com.example.filelistview.api.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Who;
    private Integer FileNum;
    private Integer MistakeNum;
    private Date RequestTime;
    private String Result;


    public String getWho() {
        return Who;
    }

    public void setWho(String who) {
        Who = who;
    }
    public Date getRequestTime() {
        return RequestTime;
    }

    public void setRequestTime(Date RequestTime) {
        RequestTime = RequestTime;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
