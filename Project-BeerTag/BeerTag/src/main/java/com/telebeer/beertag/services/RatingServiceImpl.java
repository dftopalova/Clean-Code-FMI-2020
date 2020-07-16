package com.telebeer.beertag.services;

import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.repositories.contracts.BeerRepository;
import com.telebeer.beertag.repositories.contracts.RatingRepository;
import com.telebeer.beertag.repositories.contracts.UserRepository;
import com.telebeer.beertag.services.contracts.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository repository;
    private UserRepository userRepository;
    private BeerRepository beerRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository repository,
                             UserRepository userRepository,
                             BeerRepository beerRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
        this.beerRepository = beerRepository;
    }

    @Override
    public void rateBeer(String username, int beerID, double rating) {

        User user = userRepository.getByUsername(username);
        Beer beer = beerRepository.getById(beerID);

        RatingId ratingId = new RatingId();
        ratingId.setUserId(user.getId());
        ratingId.setBeerId(beer.getBeerId());

        Rating ratingToAdd = new Rating();
        ratingToAdd.setRatingId(ratingId);
        ratingToAdd.setBeer(beer);
        ratingToAdd.setUser(user);
        ratingToAdd.setRating(rating);

        repository.rateBeer(user, beer, ratingToAdd);
    }

    @Override
    public Set<Rating> getBeerRatings(int beerId) {
        Beer beer = beerRepository.getById(beerId);

        return beer.getRatings();
    }

    @Override
    public int getAvgRatingByBeerId(int beerId) {
        Beer beer = beerRepository.getById(beerId);

        return (int) beer.getAverageRating();
    }
}
