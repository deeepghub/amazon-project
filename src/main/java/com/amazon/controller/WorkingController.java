package com.amazon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkingController {

    @GetMapping("/working")
    public ResponseEntity<String> isWorking() {
        return new ResponseEntity<>("Yes, Application is working fine!!!", HttpStatus.OK);
    }
}
