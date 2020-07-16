package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.entities.Country;

import java.util.Map;

public interface CountryRepository {

    Map<Integer, Country> getAll();

    Map<Integer, Country> getAllByContinentCode(String continentCode);

    Country getById(int id);

    Country getByName(String name);

    Country getByCode(String code);
}
