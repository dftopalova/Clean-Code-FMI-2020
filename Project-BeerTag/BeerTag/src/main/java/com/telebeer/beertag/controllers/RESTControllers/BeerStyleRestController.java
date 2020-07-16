package com.telebeer.beertag.controllers.RESTControllers;

import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.services.contracts.BeerStyleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/styles")
public class BeerStyleRestController {

    private BeerStyleService service;

    @Autowired
    public BeerStyleRestController(BeerStyleService service) {
        this.service = service;
    }

    @GetMapping
    public List<BeerStyle> getAllStyles() {
        return service.getAll();
    }

    @PostMapping
    public String createBeerStyle(@RequestBody BeerStyle beerStyle) {
        return service.createBeerStyle(beerStyle);
    }

    @GetMapping("/{id}")
    public BeerStyle getById(@PathVariable int id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBeerStyle(@PathVariable int id) {
        service.deleteBeerStyle(id);
    }

}
