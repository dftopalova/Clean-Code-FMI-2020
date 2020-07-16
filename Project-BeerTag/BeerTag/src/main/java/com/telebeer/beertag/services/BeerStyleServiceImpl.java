package com.telebeer.beertag.services;

import com.telebeer.beertag.models.entities.BeerStyle;
import com.telebeer.beertag.repositories.contracts.BeerStyleRepository;
import com.telebeer.beertag.services.contracts.BeerStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerStyleServiceImpl implements BeerStyleService {
    private BeerStyleRepository repository;

    @Autowired
    public BeerStyleServiceImpl(BeerStyleRepository repository) {
        this.repository = repository;
    }

    @Override
    public String addBeerStyle(BeerStyle beerStyle) {
        return repository.createBeerStyle(beerStyle);
    }

    @Override
    public List<BeerStyle> getAllBeerStyles() {
        return repository.getAll();
    }

    @Override
    public BeerStyle getById(int id) {
        return repository.getById(id);
    }

    @Override
    public BeerStyle getBeerStyleByName(String name) {
        return repository.getByName(name);
    }

    @Override
    public void deleteBeerStyle(int id) {
            repository.deleteBeerStyle(id);
        }
    }

