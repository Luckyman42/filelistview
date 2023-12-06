package com.example.filelistview.api.controller;


import com.example.filelistview.api.model.History;
import com.example.filelistview.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("history")
    public List<History> GetHistorys (){
        return  historyService.getAllHistory();
    }
}