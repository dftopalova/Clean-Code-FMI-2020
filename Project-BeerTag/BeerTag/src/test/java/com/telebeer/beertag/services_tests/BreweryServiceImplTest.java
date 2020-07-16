package com.telebeer.beertag.services_tests;

import com.telebeer.beertag.models.entities.Brewery;
import com.telebeer.beertag.repositories.contracts.BreweryRepository;
import com.telebeer.beertag.services.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BreweryServiceImplTest {
    @Mock
    BreweryRepository repository;
    @InjectMocks
    BreweryServiceImpl breweryServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBreweries() {
        Brewery brew1 = new Brewery();
        Brewery brew2 = new Brewery();
        List<Brewery> expected = new ArrayList<>();

        when(repository.getAll()).thenReturn(expected);

        List<Brewery> result = breweryServiceImpl.getAllBreweries();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetById() {
        Brewery brew1 = new Brewery();

        when(repository.getById(0)).thenReturn(brew1);

        Brewery result = breweryServiceImpl.getById(0);
        Assert.assertEquals(brew1, result);
    }

    @Test
    public void testGetBreweryByName() {
        Brewery brew1 = new Brewery();
        brew1.setName("brew1");

        when(repository.getByName("brew1")).thenReturn(brew1);

        Brewery result = breweryServiceImpl.getBreweryByName("brew1");
        Assert.assertEquals(brew1, result);
    }

    @Test
    public void testDeleteBrewery() {
        breweryServiceImpl.deleteBrewery(0);
    }

    @Test
    public void testAddBrewery() {
        String exp = "{\"message\": \"Brewery- %s successfully created\"}";
        when(repository.createBrewery(any())).thenReturn(exp);

        String result = breweryServiceImpl.addBrewery(new Brewery());
        Assert.assertEquals(exp, result);
    }
}
