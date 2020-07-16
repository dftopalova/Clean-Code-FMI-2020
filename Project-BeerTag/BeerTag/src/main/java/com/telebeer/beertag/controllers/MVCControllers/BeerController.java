package com.telebeer.beertag.controllers.MVCControllers;

import com.telebeer.beertag.models.dtos.*;
import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.services.contracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BeerController {

    private BeerService beerService;
    private CountryService countryService;
    private BreweryService breweryService;
    private BeerStyleService beerStyleService;

    @Autowired
    public BeerController(BeerService beerService, CountryService countryService,
                          BreweryService breweryService, BeerStyleService beerStyleService) {
        this.beerService = beerService;
        this.countryService = countryService;
        this.breweryService = breweryService;
        this.beerStyleService = beerStyleService;
    }

    @GetMapping("/beers")
    public String showAllBeers(Model model) {
        model.addAttribute("beers", beerService.getAllBeers());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in user's username from the ContextUser
        model.addAttribute("username", name);

        List<String> countries = fetchCountriesNames();
        model.addAttribute("countries", countries);

        List<String> styles = fetchBeerStylesNames();
        model.addAttribute("styles", styles);

        return "allBeers";
    }

    @GetMapping("/beers/new")
    public String newBeerForm(Model model) {
        model.addAttribute("beerDTO", new BeerDTO());

        List<String> countries = fetchCountriesNames();
        model.addAttribute("countries", countries);

        List<String> breweries = fetchBreweriesNames();
        model.addAttribute("breweries", breweries);

        List<String> styles = fetchBeerStylesNames();
        model.addAttribute("styles", styles);

        return "newBeer";
    }

    @PostMapping("/beers/new")
    public String newBeerSubmit(@Valid @ModelAttribute BeerDTO beerDTO, BindingResult bindingResult,
                                Principal principal) throws IOException {

        if (bindingResult.hasErrors()) {
            return "error";
        }

        Beer beerToCreate = beerService.migrateFromDTOToEntity(beerDTO, principal);
        beerService.createBeer(beerToCreate);

        return "redirect:/beers";
    }

    @GetMapping("/myBeers")
    public String getBeersByCreatorView(){

        return "userBeers";
    }

    private List<String> fetchBeerStylesNames() {
        List<String> styles = new ArrayList<>();
        for (BeerStyle style : beerStyleService.getAllBeerStyles()) {
            styles.add(style.getName());
        }
        return styles;
    }

    private List<String> fetchCountriesNames() {
        List<String> countries = new ArrayList<>();
        for (Country country : countryService.getAll().values()) {
            countries.add(country.getName());
        }
        return countries;
    }

    private List<String> fetchBreweriesNames() {
        List<String> breweries = new ArrayList<>();
        for (Brewery brewery: breweryService.getAllBreweries()) {
            breweries.add(brewery.getName());
        }
        return breweries;
    }
}
