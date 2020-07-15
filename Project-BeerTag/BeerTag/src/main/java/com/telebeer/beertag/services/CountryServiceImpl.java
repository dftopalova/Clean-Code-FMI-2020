package com.telebeer.beertag.services;


import com.telebeer.beertag.models.entities.Country;
import com.telebeer.beertag.repositories.contracts.CountryRepository;
import com.telebeer.beertag.services.contracts.CountryService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Map<Integer, Country> getAll() {
        return countryRepository.getAllCountries();
    }

    @Override
    public Country getCountryById(int id) {
        return countryRepository.getById(id);
    }

    @Override
    public Country getCountryByName(String name) {
        return countryRepository.getCountryByName(name);
    }

    @Override
    public Country getCountryByCode(String countryCode) {
        return countryRepository.getCountryByCode(countryCode);
    }

    @Override
    public Map<Integer, Country> getCountriesByContinentName(String continentName) {
        return countryRepository.getCountriesByContinent(continentName);
    }

}
