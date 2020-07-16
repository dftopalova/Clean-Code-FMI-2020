package com.telebeer.beertag.services;

import com.telebeer.beertag.exceptions.*;
import com.telebeer.beertag.models.dtos.BeerDTO;
import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.repositories.contracts.BeerRepository;
import com.telebeer.beertag.services.contracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeerServiceImpl implements BeerService {

    private static final String BEER_WITH_SAME_NAME_AND_STYLE_EXISTS =
            "{\"message\": \"Beer with name - %s and style %s already exists\"}";
    private static final String ALCOHOL_BY_VOLUME_INVALID_MESSAGE = "Alcohol by volume must be between 0 and 60 %!";

    private BeerRepository repository;
    private CountryService countryService;
    private BreweryService breweryService;
    private BeerStyleService beerStyleService;
    private UserService userService;

    @Autowired
    public BeerServiceImpl(BeerRepository repository, CountryService countryService,
                           BreweryService breweryService, BeerStyleService beerStyleService,
                           UserService userService) {
        this.repository = repository;
        this.countryService = countryService;
        this.breweryService = breweryService;
        this.beerStyleService = beerStyleService;
        this.userService = userService;
    }

    @Override
    public List<Beer> getAllBeers() {
        return repository.getAllBeers();
    }

    @Override
    public Beer getBeerById(int id) {
        return repository.getBeerById(id);
    }

    @Override
    public List<Beer> getBeerByName(String name) {
        return repository.getBeerByName(name);
    }

    @Override
    public void createBeer(Beer beer) {

        if (beerExists(beer.getBeerName(), beer.getStyle().getName())) {
            throw new CollisionException(String.format(BEER_WITH_SAME_NAME_AND_STYLE_EXISTS
                    , beer.getBeerName(), beer.getStyle().getName()));
        }

        if (beer.getABV() < 0 && beer.getABV() > 60) {
            throw new MalformedRequestException(ALCOHOL_BY_VOLUME_INVALID_MESSAGE);
        }

        repository.createBeer(beer);
    }

    @Override
    public String updateBeer(int id, Beer beer) {
        Beer beerToEdit = getBeerById(id);

        if (beer.getBeerName() != null && !beer.getBeerName().isEmpty()) {
            beerToEdit.setBeerName(beer.getBeerName());
        }
        if (beer.getABV() < 0 && beer.getABV() > 60) {
            throw new MalformedRequestException(ALCOHOL_BY_VOLUME_INVALID_MESSAGE);
        } else {
            beerToEdit.setABV(beer.getABV());
        }
        if (beer.getDescription() != null && !beer.getDescription().isEmpty()) {
            beerToEdit.setDescription(beer.getDescription());
        }

       return repository.updateBeer(beerToEdit);
    }

    @Override
    public void deleteBeer(int id) {
        repository.removeBeer(id);
    }

    @Override
    public List<Beer> getBeersByCreator(String creatorUsername) {
        User user = userService.getByUsername(creatorUsername);

        return repository.getBeersByCreator(user.getId());
    }

    @Override
    public List<Beer> filterBeers(String style, String countryName) {
        List<Beer> result;

        if (style == null || style.isEmpty() && !countryName.isEmpty()) {
            result = getBeersByCountry(countryName);
        } else if (countryName == null || countryName.isEmpty() && !style.isEmpty()) {
            result = getBeersByStyle(style);
        } else {
            result = getBeersByStyleAndCountry(style, countryName);
        }

        return result;

    }

    @Override
    public List<Beer> getBeersByStyle(String style) {
        List<Beer> result = getAllBeers()
                .stream()
                .filter(beer -> beer.getStyle().getName().equalsIgnoreCase(style))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<Beer> getBeersByCountry(String countryName) {
        List<Beer> result = getAllBeers()
                .stream()
                .filter(beer -> beer.getOriginCountry().getName().equalsIgnoreCase(countryName))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<Beer> getBeersByStyleAndCountry(String style, String countryName) {
        List<Beer> result = getAllBeers()
                .stream()
                .filter(beer -> beer.getOriginCountry().getName().equalsIgnoreCase(countryName)
                        && beer.getStyle().getName().equalsIgnoreCase(style))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<Beer> sortBeers(String ABVCriteria, String ratingCriteria, String nameCriteria) {
        List<Beer> result = getAllBeers();

        if (ABVCriteria != null && !ABVCriteria.isEmpty()) {
            result = sortByABV(ABVCriteria);
        } else if (ratingCriteria != null && !ratingCriteria.isEmpty()) {
            result = sortByRating(ratingCriteria);
        } else if (nameCriteria != null && !nameCriteria.isEmpty()) {
            result = sortByName(nameCriteria);
        }

        return result;
    }

    private List<Beer> sortByName(String nameCriteria) {
        List<Beer> result = getAllBeers();

        nameCriteria = nameCriteria.toLowerCase();
        switch (nameCriteria) {
            case "asc":
                result = result.stream()
                        .sorted(Comparator.comparing(Beer::getBeerName))
                        .collect(Collectors.toList());
                break;

            case "desc":
                result = result.stream()
                        .sorted(Comparator.comparing(Beer::getBeerName).reversed())
                        .collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException("No such sorting criteria!");
        }

        return result;

    }

    private List<Beer> sortByRating(String ratingCriteria) {
        List<Beer> result = getAllBeers();

        ratingCriteria = ratingCriteria.toLowerCase();
        switch (ratingCriteria) {
            case "asc":
                result = result.stream()
                        .sorted(Comparator.comparing(Beer::getAverageRating))
                        .collect(Collectors.toList());
                break;

            case "desc":
                result = result.stream()
                        .sorted(Comparator.comparing(Beer::getAverageRating).reversed())
                        .collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException("No such sorting criteria!");
        }

        return result;

    }

    private List<Beer> sortByABV(String abvCriteria) {
        List<Beer> result = getAllBeers();

        abvCriteria = abvCriteria.toLowerCase();
        switch (abvCriteria) {
            case "asc":
                result = result.stream()
                        .sorted(Comparator.comparing(Beer::getABV))
                        .collect(Collectors.toList());
                break;

            case "desc":
                result = result.stream()
                        .sorted(Comparator.comparing(Beer::getABV).reversed())
                        .collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException("No such sorting criteria!");
        }

        return result;

    }

    @Override
    public Beer migrateFromDTOToEntity(BeerDTO beerDTO, Principal principal) throws IOException {
        Beer beer = new Beer();

        beer.setBeerName(beerDTO.getBeerName());

        beer.setABV(beerDTO.getABV());

        beer.setDescription(beerDTO.getDescription());

        Brewery brewery = breweryService.getBreweryByName(beerDTO.getBrewery());
        beer.setBrewery(brewery);

        User user = userService.getByUsername(principal.getName());
        beer.setCreator(user);

        BeerStyle beerStyle = beerStyleService.getBeerStyleByName(beerDTO.getBeerStyle());
        beer.setStyle(beerStyle);

        Country country = countryService.getCountryByName(beerDTO.getOriginCountry());
        beer.setOriginCountry(country);

        beer.setBeerPicture(beerDTO.getBeerPicture());

        return beer;
    }

    private boolean beerExists(String beerName, String beerStyle) {
        return getAllBeers().stream()
                .anyMatch(beer -> beer.getBeerName().equalsIgnoreCase(beerName)
                        && beer.getStyle().getName().equalsIgnoreCase(beerStyle));
    }

}
