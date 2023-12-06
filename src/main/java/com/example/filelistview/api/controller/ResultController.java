package com.example.filelistview.api.controller;
import com.example.filelistview.api.model.History;
import com.example.filelistview.services.HistoryService;
import com.example.filelistview.services.ListViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ResultController {
    @Autowired
    private ListViewService listViewService;

    @Autowired
    private HistoryService historyService;

    @GetMapping("result")
    public String GetResult (@RequestParam Integer n, @RequestParam Integer m){
        History currentHistory =  new History();
        currentHistory.setRequestTime( new Date());
        currentHistory.setWho("name"); //todo need to get it from settings

        String path = listViewService.GenerateDummyFiles(n,m);
        String result = listViewService.GetResult(path);

        currentHistory.setFileNum(n);
        currentHistory.setMistakeNum(m);
        currentHistory.setResult(result);

        historyService.CreateHistory(currentHistory);
        return result;
    }

}
