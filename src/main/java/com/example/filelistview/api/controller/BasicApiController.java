package com.example.filelistview.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * It s a basic controller for practicing
 */
@RestController
public class BasicApiController {

    /**
     * This is endpoints is for to test the server.
     * @return true, if not then the service is not available
     */
    @GetMapping("IsReady")
    public Boolean IsReady(){
        return true;
    }
}
