package com.telebeer.beertag.services;


import com.telebeer.beertag.models.entities.Country;
import com.telebeer.beertag.repositories.contracts.CountryRepository;
import com.telebeer.beertag.services.contracts.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Map<Integer, Country> getAll() {
        return countryRepository.getAll();
    }

    @Override
    public Country getById(int id) {
        return countryRepository.getById(id);
    }

    @Override
    public Country getByName(String name) {
        return countryRepository.getByName(name);
    }

    @Override
    public Country getByCode(String countryCode) {
        return countryRepository.getByCode(countryCode);
    }

    @Override
    public Map<Integer, Country> getCountriesByContinentName(String continentName) {
        return countryRepository.getAllByContinentCode(continentName);
    }

}
