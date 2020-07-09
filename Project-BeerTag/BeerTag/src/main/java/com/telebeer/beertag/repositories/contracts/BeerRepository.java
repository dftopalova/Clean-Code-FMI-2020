package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.Entities.Beer;

import java.util.List;

public interface BeerRepository {

    List<Beer> getAllBeers();

    Beer getBeerById(int id);

    List<Beer> getBeerByName(String name);

    void addBeer(Beer beer);

    void removeBeer(int id);

    String updateBeer(Beer beer);

    List<Beer> getBeersByCreator(int creatorId);

}
