package com.example.filelistview.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicApiController {

    @GetMapping("IsReady")
    public Boolean IsReady(){
        return true;
    }
}
