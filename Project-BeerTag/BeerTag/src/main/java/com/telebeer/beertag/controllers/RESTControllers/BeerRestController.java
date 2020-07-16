package com.telebeer.beertag.controllers.RESTControllers;

import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.services.contracts.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerRestController {

    private BeerService service;

    @Autowired
    public BeerRestController(BeerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Beer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Beer getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/")
    public List<Beer> getByName(@RequestParam String name) {
        return service.getAllByName(name);
    }

    @PostMapping
    public void createBeer(@Valid @RequestBody Beer beer) { //it must be concrete class so json deserializer can work
        service.createBeer(beer);
    }

    @PutMapping("/{id}")
    public String updateBeer(@PathVariable int id, @Valid @RequestBody Beer beer) {
        return service.updateBeer(id, beer);
    }

    @DeleteMapping("/{id}")
    public void deleteBeer(@PathVariable int id) {
        service.deleteBeer(id);
    }

    @GetMapping("/user")
    public List<Beer> getBeersByCreator(@RequestParam String username) {
        return service.getBeersByCreator(username);
    }

    @GetMapping("/filter")
    public List<Beer> filterBeers(@RequestParam(required = false) String style,
                                  @RequestParam(required = false) String country) {
        return service.filterBeers(style, country);
    }

    @GetMapping("/sort")
    public List<Beer> sortBeers(@RequestParam(required = false) String ABVCriteria,
                                @RequestParam(required = false) String ratingCriteria,
                                @RequestParam(required = false) String nameCriteria) {
        return service.sortBeers(ABVCriteria, ratingCriteria, nameCriteria);
    }

}
