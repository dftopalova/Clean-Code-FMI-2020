package com.telebeer.beertag.controllers.RESTControllers;

import com.telebeer.beertag.exceptions.*;
import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public String addUser(@Valid @RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/")
    public List<User> getByNames(@RequestParam(required = false) String firstName,
                                 @RequestParam(required = false) String lastName) {

        return service.getByBothNames(firstName, lastName);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id,
                             @RequestBody(required = false) User user) {

        return service.updateUser(id, user);
    }

    @PutMapping("/username/{username}")
    public String updateUserByUsername(@PathVariable String username,
                                       @RequestBody(required = false) User user) {

        int userId = getUserByUsername(username).getId();
        user.setId(userId);

        return service.updateUser(userId, user);
    }

    @DeleteMapping("/delete/{id}")
    public void hardDeleteUser(@PathVariable int id) {
        service.hardDeleteUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(id);
    }

    @GetMapping("/sort")
    public List<User> sort(@RequestParam(value = "direction", required = false) String direction,
                           @RequestParam(value = "firstName", required = false) String firstName,
                           @RequestParam(value = "lastName", required = false) String lastName) {
        return service.sort(direction, firstName, lastName);
    }

    @GetMapping("/filter")
    public List<User> filter(@RequestParam(value = "firstName", required = false) String firstName,
                             @RequestParam(value = "lastName", required = false) String lastName) {

        return service.getByBothNames(firstName, lastName);
    }

    @GetMapping("/{username}/wishlist")
    public Set<Beer> getUserWishlist(@PathVariable String username) {
        return service.getUserWishlist(username);
    }

    @GetMapping("/{username}/testedBeers")
    public Set<Beer> getUserTestedBeers(@PathVariable String username) {
        return service.getUserTestedBeers(username);
    }

    @PutMapping("/{username}/markAsTested")
    public void markBeerAsTested(@PathVariable String username, @RequestParam int beerId) {
        service.markBeerAsTested(username, beerId);
    }

    @PutMapping("/{username}/markAsWish")
    public void markBeerAsWish(@PathVariable String username, @RequestParam int beerId) {
        service.markBeerAsWish(username, beerId);
    }

    @DeleteMapping("/{username}/testedBeers")
    public void removeBeerFromTestedList(@PathVariable String username, @RequestParam int beerId) {
        service.removeBeerFromTestedList(username, beerId);
    }

    @DeleteMapping("/{username}/wishlist")
    public void removeBeerFromWishlist(@PathVariable String username, @RequestParam int beerId) {
        service.removeBeerFromWishList(username, beerId);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return service.getByUsername(username);
    }

}

