package com.example.springcloudconsume.resttemplate;

import com.example.springcloudconsume.Ingredient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RestTemplateClient {
    private final RestTemplate restTemplate;

    public RestTemplateClient(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Ingredient> getIngredients() {
        Ingredient[] result = restTemplate.getForObject("http://ingredient-service/ingredients", Ingredient[].class);
        if (result == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(result);
    }
}
