package com.example.client.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RefreshScope
public class ClientController {

    @Value("${msg}")
    private String message;

    @GetMapping("")
    public String getMessage() {
        return message;
    }
}
