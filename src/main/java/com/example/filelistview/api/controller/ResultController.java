package com.example.filelistview.api.controller;
import com.example.filelistview.data.History;
import com.example.filelistview.services.DummyFileGenerator;
import com.example.filelistview.services.HistoryService;
import com.example.filelistview.services.ListViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ResultController {
    @Autowired
    private ListViewService listViewService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    DummyFileGenerator dummyFileGenerator;

    @Autowired
    private Environment environment;

    @GetMapping("result")
    public String GetResult (@RequestParam Integer n, @RequestParam Integer m){
        History currentHistory =  new History();
        currentHistory.setRequestTime( new Date().getTime());

        String name =  environment.getProperty("ServerName");
        if(name == null) name = "DefaultName";
        currentHistory.setWho(name);

        String path = dummyFileGenerator.GenerateDummyFiles(n,m);
        String result = listViewService.GetResult(path);
        dummyFileGenerator.DeleteDummyFiles(path);

        currentHistory.setFileNum(n);
        currentHistory.setMistakeNum(m);
        currentHistory.setResult(result);

        historyService.CreateHistory(currentHistory);
        return result;
    }

}
