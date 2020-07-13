package com.telebeer.beertag.services;

import com.telebeer.beertag.models.Entities.BeerStyle;
import com.telebeer.beertag.repositories.contracts.BeerStyleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class BeerStyleServiceImplTest {
    @Mock
    BeerStyleRepository repository;
    @InjectMocks
    BeerStyleServiceImpl beerStyleServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddBeerStyle() {
        String exp = "{\"message\": \"Beer style- %s successfully added\"}";

        when(repository.addBeerStyle(any())).thenReturn(exp);

        String result = beerStyleServiceImpl.addBeerStyle(new BeerStyle());
        Assert.assertEquals(exp, result);
    }

    @Test
    public void testGetAllBeerStyles()  {
        BeerStyle style1 = new BeerStyle();
        BeerStyle style2 = new BeerStyle();
        List<BeerStyle> exp = new ArrayList<>();
        exp.add(style1);
        exp.add(style2);

        when(repository.getAllBeerStyles()).thenReturn(exp);

        List<BeerStyle> result = beerStyleServiceImpl.getAllBeerStyles();
        Assert.assertEquals(exp, result);
    }

    @Test
    public void testGetById() {
        BeerStyle style1 = new BeerStyle();

        when(repository.getById(0)).thenReturn(style1);

        BeerStyle result = beerStyleServiceImpl.getById(0);
        Assert.assertEquals(style1, result);
    }

    @Test
    public void testGetBeerStyleByName() {
        BeerStyle style1 = new BeerStyle();
        style1.setName("style1");

        when(repository.getBeerStyleByName("style1")).thenReturn(style1 );

        BeerStyle result = beerStyleServiceImpl.getBeerStyleByName("style1");
        Assert.assertEquals(style1, result);
    }

    @Test
    public void testDeleteBeerStyle() {
        beerStyleServiceImpl.deleteBeerStyle(0);
    }
}

