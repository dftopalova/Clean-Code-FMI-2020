package com.telebeer.beertag.controllers.RESTControllers;

import com.telebeer.beertag.models.entities.Rating;
import com.telebeer.beertag.services.contracts.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/ratings")
public class RatingRestController {

    private RatingService service;

    @Autowired
    public RatingRestController(RatingService service) {
        this.service = service;
    }

    @PostMapping
    public void rateBeer(@RequestParam(value = "username") String username, @RequestParam(value = "beerId") int beerId,
                         @RequestParam(value = "rating") double rating) {
        service.rateBeer(username, beerId, rating);
    }

    @GetMapping
    public Set<Rating> getBeerRatings(@RequestParam int beerId) {
        return service.getBeerRatings(beerId);
    }

    @GetMapping("/beer/{id}")
    public int getBeerAvgRating(@PathVariable int id) {
        return service.getAvgRatingByBeerId(id);
    }
}
