package com.telebeer.beertag.controllers.RESTControllers;

import com.telebeer.beertag.models.entities.Country;
import com.telebeer.beertag.services.contracts.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/countries")
public class CountryRestController {


    private CountryService service;

    @Autowired
    public CountryRestController(CountryService service) {
        this.service = service;
    }


    @GetMapping
    public Map<Integer, Country> getAlLCountries() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable int id) {
        return service.getCountryById(id);
    }

    @GetMapping("/name")
    public Country sort(@RequestParam(value = "value") String countryName) {
        return service.getCountryByName(countryName);
    }

    @GetMapping("/continent")
    public Map<Integer, Country> getCountriesByContinentName (@RequestParam(value = "code") String name) {
        return service.getCountriesByContinentName(name);
    }

    @GetMapping("/code")
    public Country getCountriesByCountryCode (@RequestParam(value = "value") String code) {
        return service.getCountryByCode(code);
    }

}
