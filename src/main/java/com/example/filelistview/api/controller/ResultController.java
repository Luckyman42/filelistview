package com.example.filelistview.api.controller;
import com.example.filelistview.data.History;
import com.example.filelistview.services.DummyFileGenerator;
import com.example.filelistview.services.HistoryService;
import com.example.filelistview.services.ListViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * This controller is for the Result, the pretty print endpoints for the file list view
 */
@Tag(name = "Files Linear Tree View", description = "Get formatted string of a linear tree view")
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

    /**
     * Endpoints for the pretty print
     * @param n is the number of the correct files
     * @param m is the number of the mistaken files
     * @return A string which is formatted with \n \t.
     */
    @Operation(
            summary = "Get Result",
            description = "Generate temporary files and then create a pretty view for its liner tree view")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("result")
    public String GetResult (@RequestParam Integer n, @RequestParam Integer m){
        History currentHistory =  new History();
        currentHistory.setRequestTime( new Date().getTime());

        String name =  environment.getProperty("SERVER_NAME");
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
