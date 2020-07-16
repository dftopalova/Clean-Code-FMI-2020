package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.entities.*;

public interface RatingRepository {

    void rateBeer(User user, Beer beer, Rating rating);

}
