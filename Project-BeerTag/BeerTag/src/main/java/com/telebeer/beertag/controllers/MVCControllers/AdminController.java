package com.telebeer.beertag.controllers.MVCControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public AdminController() {
    }

    @GetMapping("/users")
    public String adminAllUsers() {

        return "adminUsers";
    }

    @GetMapping("/tags")
    public String adminAllTags() {

        return "adminTags";
    }

    @GetMapping("/breweries")
    public String adminAllBreweries() {

        return "adminBreweries";
    }

    @GetMapping("/styles")
    public String adminAllStyles() {

        return "adminStyles";
    }


}
