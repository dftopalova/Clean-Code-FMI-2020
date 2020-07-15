package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.entities.*;

import java.util.List;

public interface BreweryRepository {
    List<Brewery> getAllBreweries();

    String addBrewery(Brewery brewery);

    Brewery getById(int id);

    Brewery getBreweryByName(String name);

    void deleteBrewery(int id);
}
