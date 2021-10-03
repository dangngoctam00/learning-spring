package com.example.springcloudconsume.resttemplate;

import com.example.springcloudconsume.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@Slf4j
public class RestTemplateController {

    private final RestTemplateClient client;

    @Autowired
    public RestTemplateController(RestTemplateClient client) {
        this.client = client;
    }

    @GetMapping("")
    public List<Ingredient> getIngredients() {
        log.info("GET API is called!");
        return client.getIngredients();
    }
}
