package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.Entities.Country;

import java.util.Map;

public interface CountryRepository {

    Map<Integer, Country> getAllCountries();

    Map<Integer, Country> getCountriesByContinent(String continentCode);

    Country getById(int id);

    Country getCountryByName(String name);

    Country getCountryByCode(String code);
}
