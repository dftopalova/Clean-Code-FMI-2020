package com.telebeer.beertag.services;

import com.telebeer.beertag.models.entities.Brewery;
import com.telebeer.beertag.repositories.contracts.BreweryRepository;
import com.telebeer.beertag.services.contracts.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreweryServiceImpl implements BreweryService {

    private BreweryRepository repository;

    @Autowired
    public BreweryServiceImpl(BreweryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Brewery> getAll(){
        return repository.getAll();
    }

    @Override
    public Brewery getById(int id){
        return repository.getById(id);
    }

    @Override
    public Brewery getByName(String name){
        return repository.getByName(name);
    }

    @Override
    public void deleteBrewery(int id) {
        repository.deleteBrewery(id);
    }

    @Override
    public String createBrewery(Brewery brewery) {
       return repository.createBrewery(brewery);
    }
}
