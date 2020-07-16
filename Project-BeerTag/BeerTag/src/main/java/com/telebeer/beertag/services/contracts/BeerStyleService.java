package com.telebeer.beertag.services.contracts;

import com.telebeer.beertag.models.entities.BeerStyle;

import java.util.List;

public interface BeerStyleService {
    List<BeerStyle> getAll();

    BeerStyle getById(int id);

    BeerStyle getByName(String name);

    String createBeerStyle(BeerStyle beerStyle);

    void deleteBeerStyle(int id);
}
