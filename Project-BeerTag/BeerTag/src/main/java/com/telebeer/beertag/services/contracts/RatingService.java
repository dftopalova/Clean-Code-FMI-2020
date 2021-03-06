package com.telebeer.beertag.services.contracts;

import com.telebeer.beertag.models.entities.*;

import java.util.Set;

public interface RatingService {
    void rateBeer(String username, int beerID, double rating);

    Set<Rating> getBeerRatings(int beerID);

    int getAvgRatingByBeerId(int beerID);
}
