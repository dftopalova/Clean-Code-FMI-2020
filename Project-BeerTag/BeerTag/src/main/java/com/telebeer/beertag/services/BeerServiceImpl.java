package com.telebeer.beertag.services;

import com.telebeer.beertag.models.dtos.BeerDTO;
import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.repositories.contracts.BeerRepository;
import com.telebeer.beertag.services.contracts.*;
import com.telebeer.beertag.utilities.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.telebeer.beertag.utilities.constants.BeerConstants.*;

@Service
public class BeerServiceImpl implements BeerService {

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
    public List<Beer> getAll() {
        return repository.getAll();
    }

    @Override
    public Beer getById(int id) {
        return repository.getById(id);
    }

    @Override
    public List<Beer> getAllByName(String name) {
        return repository.getBeerByName(name);
    }

    @Override
    public void createBeer(Beer beer) {

        if (beerExists(beer.getName(), beer.getStyle().getName())) {
            throw new CollisionException(String.format(BEER_WITH_SAME_NAME_AND_STYLE_EXISTS
                    , beer.getName(), beer.getStyle().getName()));
        }

        if (ValidationHelper.isNumberInRange(beer.getABV(), MIN_ALCOHOL_BY_VOLUME_VALUE, MAX_ALCOHOL_BY_VOLUME_VALUE)) {
            throw new MalformedRequestException(ALCOHOL_BY_VOLUME_INVALID_MESSAGE);
        }

        repository.createBeer(beer);
    }

    @Override
    public String updateBeer(int id, Beer beer) {
        Beer beerToEdit = getById(id);

        if (ValidationHelper.isStringValid(beer.getName())) {
            beerToEdit.setName(beer.getName());
        }
        if (ValidationHelper.isNumberInRange(beer.getABV(), MIN_ALCOHOL_BY_VOLUME_VALUE, MAX_ALCOHOL_BY_VOLUME_VALUE)) {
            throw new MalformedRequestException(ALCOHOL_BY_VOLUME_INVALID_MESSAGE);
        } else {
            beerToEdit.setABV(beer.getABV());
        }
        if (ValidationHelper.isStringValid(beer.getDescription())) {
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

    public List<Beer> getBeersByStyle(String style) {
        List<Beer> result = getAll()
                .stream()
                .filter(beer -> beer.getStyle().getName().equalsIgnoreCase(style))
                .collect(Collectors.toList());

        return result;
    }

    public List<Beer> getBeersByCountry(String countryName) {
        List<Beer> result = getAll()
                .stream()
                .filter(beer -> beer.getOriginCountry().getName().equalsIgnoreCase(countryName))
                .collect(Collectors.toList());

        return result;
    }

    public List<Beer> getBeersByStyleAndCountry(String style, String countryName) {
        List<Beer> result = getAll()
                .stream()
                .filter(beer -> beer.getOriginCountry().getName().equalsIgnoreCase(countryName)
                        && beer.getStyle().getName().equalsIgnoreCase(style))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<Beer> sortBeers(String ABVCriteria, String ratingCriteria, String nameCriteria) {
        List<Beer> result = getAll();

        if (ValidationHelper.isStringValid(ABVCriteria)) {
            result = sortByABV(ABVCriteria);
        } else if (ValidationHelper.isStringValid(ratingCriteria)) {
            result = sortByRating(ratingCriteria);
        } else if (ValidationHelper.isStringValid(nameCriteria)) {
            result = sortByName(nameCriteria);
        }

        return result;
    }

    private List<Beer> sortByName(String nameCriteria) {
        List<Beer> result = getAll();

        nameCriteria = nameCriteria.toLowerCase();
        switch (nameCriteria) {
            case "asc":
                result = result.stream()
                        .sorted(Comparator.comparing(Beer::getName))
                        .collect(Collectors.toList());
                break;

            case "desc":
                result = result.stream()
                        .sorted(Comparator.comparing(Beer::getName).reversed())
                        .collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException(ERR_NO_SUCH_SORTING_CRITERIA);
        }

        return result;

    }

    private List<Beer> sortByRating(String ratingCriteria) {
        List<Beer> result = getAll();

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
                throw new IllegalArgumentException(ERR_NO_SUCH_SORTING_CRITERIA);
        }

        return result;

    }

    private List<Beer> sortByABV(String abvCriteria) {
        List<Beer> result = getAll();

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
                throw new IllegalArgumentException(ERR_NO_SUCH_SORTING_CRITERIA);
        }

        return result;

    }

    @Override
    public Beer migrateFromDTOToEntity(BeerDTO beerDTO, Principal principal) throws IOException {
        Beer beer = new Beer();

        beer.setName(beerDTO.getName());

        beer.setABV(beerDTO.getABV());

        beer.setDescription(beerDTO.getDescription());

        Brewery brewery = breweryService.getByName(beerDTO.getBrewery());
        beer.setBrewery(brewery);

        User user = userService.getByUsername(principal.getName());
        beer.setCreator(user);

        BeerStyle beerStyle = beerStyleService.getByName(beerDTO.getBeerStyle());
        beer.setStyle(beerStyle);

        Country country = countryService.getByName(beerDTO.getOriginCountry());
        beer.setOriginCountry(country);

        beer.setBeerPicture(beerDTO.getPicture());

        return beer;
    }

    private boolean beerExists(String beerName, String beerStyle) {
        return getAll().stream()
                .anyMatch(beer -> beer.getName().equalsIgnoreCase(beerName)
                        && beer.getStyle().getName().equalsIgnoreCase(beerStyle));
    }

}
