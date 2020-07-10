package com.telebeer.beertag.services.contracts;

import com.telebeer.beertag.models.DTOs.BeerDTO;
import com.telebeer.beertag.models.Entities.Beer;
import com.telebeer.beertag.models.entities.Beer;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface BeerService {
    List<Beer> getAllBeers();

    Beer getBeerById(int id);

    List<Beer> getBeerByName(String name);

    void addBeer(Beer beer);

    String updateBeer(int id, Beer beer);

    void deleteBeer(int id);

    List<Beer> getBeersByCreator(String creatorUsername);

    List<Beer> getBeersByStyle(String style);

    List<Beer> getBeersByCountry(String countryName);

    List<Beer> getBeersByStyleAndCountry(String style, String countryName);

    List<Beer> filterBeers(String style, String countryName);

    List<Beer> sortBeers(String ABVCriteria, String ratingCriteria,
                         String nameCriteria);

    Beer migrateFromDTOToEntity(BeerDTO beerDTO, Principal principal) throws IOException;

}
