package com.telebeer.beertag.services.contracts;

import com.telebeer.beertag.models.entities.BeerStyle;

import java.util.List;

public interface BeerStyleService {
    String addBeerStyle(BeerStyle beerStyle);

    List<BeerStyle> getAllBeerStyles();

    BeerStyle getById(int id);

    BeerStyle getBeerStyleByName(String name);

    void deleteBeerStyle(int id);
}
