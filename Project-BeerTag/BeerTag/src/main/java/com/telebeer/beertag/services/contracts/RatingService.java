package com.telebeer.beertag.services.contracts;

import com.telebeer.beertag.models.Entities.Rating;

import java.util.Set;

public interface RatingService {
    void rateBeer(String username, int beerID, double rating);

    Set<Rating> getBeerRatings(int beerId);

    int getAvgRatingByBeerId(int beerid);
}
