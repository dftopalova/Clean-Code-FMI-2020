package com.telebeer.beertag.services;

import com.telebeer.beertag.models.Entities.Beer;
import com.telebeer.beertag.models.Entities.Rating;
import com.telebeer.beertag.models.Entities.User;
import com.telebeer.beertag.repositories.contracts.BeerRepository;
import com.telebeer.beertag.repositories.contracts.RatingRepository;
import com.telebeer.beertag.repositories.contracts.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class RatingServiceImplTest {
    @Mock
    RatingRepository repository;
    @Mock
    UserRepository userRepository;
    @Mock
    BeerRepository beerRepository;
    @InjectMocks
    RatingServiceImpl ratingServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRateBeer() {
        User user = new User();
        user.setUserName("username");
        Beer beer = new Beer();
        beer.setBeerId(0);
        beer.setBeerName("name");

        Rating rating = new Rating();
        rating.setRating(5);
        rating.setBeer(beer);

        when(userRepository.getByUsername("username")).thenReturn(user);
        when(beerRepository.getBeerById(0)).thenReturn(beer);

        ratingServiceImpl.rateBeer("username", 0, 0d);
    }

    @Test
    public void testGetBeerRatings() {
        User user = new User();
        user.setUserName("username");
        Beer beer = new Beer();
        beer.setBeerId(0);
        beer.setBeerName("name");


        Rating rating = new Rating();
        rating.setRating(5);
        rating.setBeer(beer);

        when(beerRepository.getBeerById(0)).thenReturn(beer);
        Set<Rating> expected = new HashSet<>();
        expected.add(rating);
        beer.setRatings(expected);
        Set<Rating> result = ratingServiceImpl.getBeerRatings(0);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetAvgRatingByBeerId() {
        when(beerRepository.getBeerById(anyInt())).thenReturn(new Beer());

        int result = ratingServiceImpl.getAvgRatingByBeerId(0);
        Assert.assertEquals(0, result);
    }
}