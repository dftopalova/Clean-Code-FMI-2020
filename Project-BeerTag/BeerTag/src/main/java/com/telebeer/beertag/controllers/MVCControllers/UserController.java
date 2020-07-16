package com.telebeer.beertag.controllers.MVCControllers;

import com.telebeer.beertag.services.contracts.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsersPage(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/profile")
    public String getProfilePage() {
        return "profile";
    }

    @GetMapping("/editProfile")
    public String getEditProfilePage() {
        return "editProfile";
    }

    @GetMapping("/wishlist")
    public String getUserWishlistPage() {

        return "wishlist";
    }

    @GetMapping("/drankBeers")
    public String getUserDrankBeersPage() {
        return "drankBeers";
    }

    @GetMapping("/myBeers")
    public String getUserCreatedBeersPage() {
        return "myBeers";
    }

}
