package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.entities.*;

import java.util.List;

public interface BeerStyleRepository {
    String addBeerStyle(BeerStyle beerStyle);

        List<BeerStyle> getAllBeerStyles();

    BeerStyle  getById(int id);

    BeerStyle getBeerStyleByName(String name);

     void deleteBeerStyle(int id);

    }
