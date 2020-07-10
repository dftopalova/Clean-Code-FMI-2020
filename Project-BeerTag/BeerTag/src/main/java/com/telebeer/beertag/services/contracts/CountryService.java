package com.telebeer.beertag.services.contracts;

import com.telebeer.beertag.models.Entities.Country;

import java.util.Map;

public interface CountryService {

    Map<Integer, Country> getAll() ;

    Country getCountryById(int id);

    Country getCountryByName(String name);

    Country getCountryByCode(String countryCode);

    Map<Integer, Country> getCountriesByContinentName(String continentName);
}
