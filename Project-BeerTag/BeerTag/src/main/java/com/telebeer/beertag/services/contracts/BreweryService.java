package com.telebeer.beertag.services.contracts;

import com.telebeer.beertag.models.entities.Brewery;

import java.util.List;

public interface BreweryService {
    List<Brewery> getAllBreweries();

    Brewery getById(int id);

    Brewery getBreweryByName(String name);

    void deleteBrewery(int id);

    String addBrewery(Brewery brewery);

}
