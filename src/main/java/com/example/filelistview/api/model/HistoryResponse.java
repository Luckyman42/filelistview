package com.example.filelistview.api.model;

import com.example.filelistview.data.History;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This response is for the History object
 * Makes it more readable
 */
public class HistoryResponse {
    private String Who;
    private Integer FileNum;
    private Integer MistakeNum;
    private String RequestTime;
    private String Result;

    /**
     * Copy all field and make the request time is readable for human beings
     * @param history is the instance of the original history.
     */
    public HistoryResponse(History history){
        this.Who = history.getWho();
        this.FileNum = history.getFileNum();
        this.MistakeNum = history.getMistakeNum();
        this.Result = history.getResult();
        Date date = new Date(history.getRequestTime());
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        this.RequestTime = format.format(date);
    }

    /**
     * This is a getter for who
     * @return The value of Who
     */
    public String getWho() {
        return Who;
    }

    /**
     * This is a Setter for Who
     * @param who the new value of Who
     */
    public void setWho(String who) {
        Who = who;
    }

    /**
     * This is a getter for RequestTime
     * @return The value of RequestTime
     */
    public String getRequestTime() {
        return RequestTime;
    }

    /**
     * This is a Setter for RequestTime
     * @param RequestTime the new value of RequestTime
     */
    public void setRequestTime(String RequestTime) { this.RequestTime = RequestTime; }

    /**
     * This is a getter for Result
     * @return The value of Result
     */
    public String getResult() {
        return Result;
    }

    /**
     * This is a Setter for result
     * @param result the new value of result
     */
    public void setResult(String result) {
        Result = result;
    }

    /**
     * This is a getter for fileNum
     * @return The value of fileNum
     */
    public Integer getFileNum() {
        return FileNum;
    }

    /**
     * This is a Setter for fileNum
     * @param fileNum the new value of fileNum
     */
    public void setFileNum(Integer fileNum) {
        FileNum = fileNum;
    }

    /**
     * This is a getter for MistakeNum
     * @return The value of MistakeNum
     */
    public Integer getMistakeNum() {
        return MistakeNum;
    }

    /**
     * This is a Setter for mistakeNum
     * @param mistakeNum the new value of mistakeNum
     */
    public void setMistakeNum(Integer mistakeNum) {
        MistakeNum = mistakeNum;
    }

}
