package com.example.geektrustproblems.controller;

import java.util.List;

import com.example.geektrustproblems.service.InputLoaderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/upload")
public class InputFileController {

    @Autowired
    private InputLoaderService inputLoaderService;

    @PostMapping
    public List<String> create(@RequestParam("file") String file) {
        return inputLoaderService.loadInputFile(file);
    }

}