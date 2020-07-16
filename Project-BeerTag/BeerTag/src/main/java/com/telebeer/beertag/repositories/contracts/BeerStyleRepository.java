package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.entities.*;

import java.util.List;

public interface BeerStyleRepository {

    List<BeerStyle> getAll();

    BeerStyle getById(int id);

    BeerStyle getByName(String name);

    String createBeerStyle(BeerStyle beerStyle);

    void deleteBeerStyle(int id);

}
