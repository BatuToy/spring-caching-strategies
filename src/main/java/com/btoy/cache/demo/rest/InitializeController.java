package com.btoy.cache.demo.rest;

import com.btoy.cache.demo.InitializeData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(value = "")
@RestController
public class InitializeController {

    private final InitializeData initializeData;

    @PostMapping(value = "/init")
    public ResponseEntity<Void> init() {
        initializeData.persistData();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
