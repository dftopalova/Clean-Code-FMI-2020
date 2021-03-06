package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.entities.*;

import java.util.List;

public interface BeerRepository {

    List<Beer> getAll();

    Beer getById(int id);

    List<Beer> getBeerByName(String name);

    void createBeer(Beer beer);

    void removeBeer(int id);

    String updateBeer(Beer beer);

    List<Beer> getBeersByCreator(int creatorId);

}
