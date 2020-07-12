package com.telebeer.beertag.controllers.RESTControllers;


import com.telebeer.beertag.models.Entities.Brewery;
import com.telebeer.beertag.models.Entities.Country;
import com.telebeer.beertag.services.contracts.BreweryService;
import com.telebeer.beertag.services.contracts.CountryService;
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
        return service.getAllBreweries();
    }

    @PostMapping
    public String addBrewery(@RequestBody Brewery brewery) {
       return service.addBrewery(brewery);
    }

    @GetMapping("/{id}")
    public Brewery getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/brewery")
    public Brewery getBreweryByName(@RequestParam String name) {
        return service.getBreweryByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteBrewery(@PathVariable int id) {
        service.deleteBrewery(id);
    }


}
