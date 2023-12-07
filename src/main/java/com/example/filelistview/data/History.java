package com.example.filelistview.data;
import jakarta.persistence.*;

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Who;
    private Integer FileNum;
    private Integer MistakeNum;
    private long RequestTime;
    @Column(columnDefinition = "text")
    private String Result;


    public String getWho() {
        return Who;
    }

    public void setWho(String who) {
        Who = who;
    }
    public long getRequestTime() {
        return RequestTime;
    }

    public void setRequestTime(long RequestTime) {
        this.RequestTime = RequestTime;
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
