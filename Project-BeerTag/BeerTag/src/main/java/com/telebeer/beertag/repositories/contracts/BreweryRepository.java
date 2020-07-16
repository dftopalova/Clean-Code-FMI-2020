package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.entities.*;

import java.util.List;

public interface BreweryRepository {

    List<Brewery> getAll();

    Brewery getById(int id);

    Brewery getByName(String name);

    String createBrewery(Brewery brewery);

    void deleteBrewery(int id);

}
