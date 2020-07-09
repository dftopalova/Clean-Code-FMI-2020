package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.Entities.Brewery;

import java.util.List;

public interface BreweryRepository {
    List<Brewery> getAllBreweries();

    String addBrewery(Brewery brewery);

    Brewery getById(int id);

    Brewery getBreweryByName(String name);

    void deleteBrewery(int id);
}
