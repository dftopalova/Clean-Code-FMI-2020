package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.Entities.Beer;
import com.telebeer.beertag.models.entities.Rating;
import com.telebeer.beertag.models.Entities.User;

public interface RatingRepository {
    void rateBeer(User user, Beer beer, Rating rating);


}
