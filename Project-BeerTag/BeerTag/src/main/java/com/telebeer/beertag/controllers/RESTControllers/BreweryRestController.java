package com.telebeer.beertag.controllers.RESTControllers;

import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.services.contracts.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/breweries")
public class BreweryRestController {

    private BreweryService service;

    @Autowired
    public BreweryRestController(BreweryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Brewery> getAllBreweries() {
        return service.getAll();
    }

    @PostMapping
    public String createBrewery(@RequestBody Brewery brewery) {
       return service.createBrewery(brewery);
    }

    @GetMapping("/{id}")
    public Brewery getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/brewery")
    public Brewery getBreweryByName(@RequestParam String name) {
        return service.getByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteBrewery(@PathVariable int id) {
        service.deleteBrewery(id);
    }


}
