package com.telebeer.beertag.services;

import com.telebeer.beertag.models.Entities.Country;
import com.telebeer.beertag.repositories.contracts.CountryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class CountryServiceImplTest {
    @Mock
    CountryRepository countryRepository;
    @InjectMocks
    CountryServiceImpl countryServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        Map<Integer, Country> result = new HashMap<>();
        Country country1 = new Country();
        country1.setDeleted(false);
        Country country2 = new Country();
        country2.setDeleted(false);

        when(countryRepository.getAllCountries()).thenReturn(result);

        Map<Integer, Country> expected = countryServiceImpl.getAll();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetCountryById() {
        Country country1 = new Country();
        country1.setDeleted(false);
        Country country2 = new Country();
        country2.setDeleted(false);

        when(countryRepository.getById(0)).thenReturn(country1);

        Country result = countryServiceImpl.getCountryById(0);
        Assert.assertEquals(country1, result);
    }

    @Test
    public void testGetCountryByName() {
        Country country1 = new Country();
        country1.setDeleted(false);
        country1.setName("country1");
        Country country2 = new Country();
        country2.setDeleted(false);
        country2.setName("country2");

        when(countryRepository.getCountryByName("country1")).thenReturn(country1);

        Country result = countryServiceImpl.getCountryByName("country1");
        Assert.assertEquals(country1, result);
    }

    @Test
    public void testGetCountryByCode() {
        Country country1 = new Country();
        country1.setDeleted(false);
        country1.setName("country1");
        country1.setCode("EU");

        when(countryRepository.getCountryByCode("EU")).thenReturn(country1);

        Country result = countryServiceImpl.getCountryByCode("EU");
        Assert.assertEquals(country1, result);
    }

    @Test
    public void testGetCountriesByContinentName() {
        Country country1 = new Country();
        country1.setDeleted(false);
        country1.setName("country1");
        country1.setContinent_code("EU");
        Country country2 = new Country();
        country2.setDeleted(false);
        country2.setName("country2");
        country2.setContinent_code("EU");

        Map<Integer, Country> expected = new HashMap<>();
        expected.put(1, country1);
        expected.put(2, country2);

        when(countryRepository.getCountriesByContinent("EU")).thenReturn(expected);

        Map<Integer, Country> result = countryServiceImpl.getCountriesByContinentName("EU");
        Assert.assertEquals(expected, result);
    }
}

