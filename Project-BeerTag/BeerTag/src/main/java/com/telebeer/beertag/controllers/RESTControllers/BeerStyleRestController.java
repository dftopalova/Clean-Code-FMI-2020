package com.telebeer.beertag.controllers.RESTControllers;

import com.telebeer.beertag.models.Entities.BeerStyle;
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
        return service.getAllBeerStyles();
    }

    @PostMapping
    public String addBeerStyle(@RequestBody BeerStyle beerStyle) {
        return service.addBeerStyle(beerStyle);
    }

    @GetMapping("/{id}")
    public BeerStyle getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/style")
    public BeerStyle getBeerStyleByName(@RequestParam String name) {
        return service.getBeerStyleByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteBeerStyle(@PathVariable int id) {
        service.deleteBeerStyle(id);
    }


}
