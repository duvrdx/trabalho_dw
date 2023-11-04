package com.theboys.trabalho.controllers;

import com.theboys.trabalho.models.category.UserStoryCategory;
import com.theboys.trabalho.models.category.EpicCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @GetMapping("/epic")
    public List<EpicCategory> epic() {
        return Arrays.asList(EpicCategory.values());
    }

    @GetMapping("/userstory")
    public List<UserStoryCategory> userstory() {
        return Arrays.asList(UserStoryCategory.values());
    }
}
