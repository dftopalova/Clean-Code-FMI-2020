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

        return service.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
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
    public String updateProfileInfo(@PathVariable int id,
                                  @RequestBody(required = false) User user) {

       return service.updateProfileInfo(id, user);
    }
    @PutMapping("/username/{username}")
    public String updateProfileInfoByUserName(@PathVariable String username,
                                  @RequestBody(required = false) User user) {
        int tempId = getUserByUsername(username).getId();
        user.setId(tempId);
      return   service.updateProfileInfo(tempId, user);
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
        if (direction == null) {
            return service.getAllUsers();
        }
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

    @GetMapping("/{username}/drankBeers")
    public Set<Beer> getUserDrankBeers(@PathVariable String username) {
        return service.getUserDrankBeers(username);
    }

    @PutMapping("/{username}/markAsDrank")
    public void markBeerAsDranked(@PathVariable String username, @RequestParam int beerId) {
        try {
            service.markBeerAsDranked(username, beerId);
        } catch (BeerAlreadyMarkedException | BeerExistsInOtherListException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{username}/markAsWish")
    public void markBeerAsWish(@PathVariable String username, @RequestParam int beerId) {
        try {
            service.markBeerAsWish(username, beerId);
        } catch (BeerAlreadyMarkedException | BeerExistsInOtherListException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{username}/drankBeers")
    public void removeBeerFromDrankList(@PathVariable String username, @RequestParam int beerId) {
        service.removeBeerFromDrankList(username, beerId);
    }

    @DeleteMapping("/{username}/wishlist")
    public void removeBeerFromWishlist(@PathVariable String username, @RequestParam int beerId) {
        service.removeBeerFromWishes(username, beerId);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return service.getByUsername(username);
    }

}

