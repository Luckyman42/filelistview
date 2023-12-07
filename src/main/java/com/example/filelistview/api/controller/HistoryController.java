package com.example.filelistview.api.controller;


import com.example.filelistview.api.model.HistoryResponse;
import com.example.filelistview.data.History;
import com.example.filelistview.services.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "History", description = "History API management")
@RestController
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @Operation(
            summary = "Get History",
            description = "Give back all request all needed data, and responses.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = HistoryResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("history")
    public List<HistoryResponse> GetHistorys (){
        return  historyService.getAllHistory().stream().map(HistoryResponse::new).collect(Collectors.toList());
    }
}