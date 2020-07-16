package com.telebeer.beertag.services.contracts;

import com.telebeer.beertag.models.entities.Country;

import java.util.Map;

public interface CountryService {

    Map<Integer, Country> getAll();

    Country getById(int id);

    Country getByName(String name);

    Country getByCode(String countryCode);

    Map<Integer, Country> getCountriesByContinentName(String continentName);
}
