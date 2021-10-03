package com.example.springcloudclients.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
@Slf4j
public class IngredientController {
    private final List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("Tomato", "TOM"),
            new Ingredient("Avocado", "AVO"),
            new Ingredient("Pepper", "PEP")
            );

    @GetMapping("")
    public List<Ingredient> getIngredients() {
        log.info("GET API is called!");
        return ingredients;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static
    class Ingredient {
        private String name;
        private String code;
    }
}
