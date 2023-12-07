package com.example.filelistview.api.controller;


import com.example.filelistview.api.model.HistoryResponse;
import com.example.filelistview.data.History;
import com.example.filelistview.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("history")
    public List<HistoryResponse> GetHistorys (){
        return  historyService.getAllHistory().stream().map(HistoryResponse::new).collect(Collectors.toList());
    }
}