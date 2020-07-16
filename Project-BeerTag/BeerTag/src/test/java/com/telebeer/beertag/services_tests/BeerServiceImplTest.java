package com.telebeer.beertag.services_tests;

import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.repositories.contracts.BeerRepository;
import com.telebeer.beertag.services.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BeerServiceImplTest {

    @Mock
    private BeerRepository mockRepository;

    @Mock
    private UserServiceImpl userServiceMock;

    @Mock
    private CountryServiceImpl countryServiceMock;

    @Mock
    private BreweryServiceImpl breweryServiceMock;

    @Mock
    private BeerStyleServiceImpl beerStyleServiceTest;

    @Mock
    private BeerServiceImpl beerServiceMock;

    @InjectMocks // creates an instance of the class and injects the mocks that are created with the @Mock
    private BeerServiceImpl beerService;

    private Beer beer;

    @Before
    public void before() {
        beer = new Beer();
        beer.setName("name");

        BeerStyle style = new BeerStyle();
        style.setName("Light");

        Country country = new Country();
        country.setName("Bulgaria");

        Brewery brewery = new Brewery();
        brewery.setName("Jagerhof");

        User user = new User();
        user.setUserName("test");
        user.setPassword("test");

        when(beerStyleServiceTest.getBeerStyleByName("Light")).thenReturn(style);
        when(countryServiceMock.getCountryByName("Bulgaria")).thenReturn(country);
        when(breweryServiceMock.getBreweryByName("Jagerhof")).thenReturn(brewery);
        when(userServiceMock.getByUsername("test")).thenReturn(user);
        beer.setStyle(style);
        beer.setOriginCountry(country);
        beer.setBrewery(brewery);
        beer.setCreator(user);

    }

    @Test
    public void getAllBeers_ShouldBeSuccessful() {
        // Arrange
        List<Beer> expectedResult = new ArrayList<>();
        expectedResult.add(beer);
        when(mockRepository.getAll()).thenReturn(expectedResult);

        // Act
        List<Beer> result = beerService.getAllBeers();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getBeerById_ShouldReturnCorrectBeer() {
        //Arrange
        when(mockRepository.getBeerById(1))
                .thenReturn(new Beer());

        //Act
        Beer result = beerService.getBeerById(1);
        result.setName("Kamenitza");

        //Assert
        assertEquals("Kamenitza", result.getName());
    }

    @Test
    public void getBeerByName_ShouldReturnCorrectBeer() {
        // Arrange
        String name = "beer";
        List<Beer> expectedResult = Arrays.asList();
        when(mockRepository.getBeerByName("beer")).thenReturn(Arrays.asList());

        // Act
        final List<Beer> result = beerService.getBeerByName(name);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void addBeer_ShouldReturnCorrectCreatedBeer() {
        //Arrange

        //Act
        beerService.createBeer(beer);

        //Assert
        verify(mockRepository).createBeer(beer);
    }

    @Test(expected = CollisionException.class)
    public void addBeer_Should_ThrowException_When_BeerNameAndStyleAreDuplicate() {
        //Arrange
        Beer testBeer = new Beer();
        testBeer.setName("name");

        BeerStyle style = new BeerStyle();
        style.setName("Light");
        testBeer.setStyle(style);

        List<Beer> beers = new ArrayList<>();
        beers.add(testBeer);

        when(beerService.getAllBeers()).thenReturn(beers);

        //Act,Assert
        beerService.createBeer(beer);
    }


    @Test
    public void updateBeer_ShouldBeSuccessful() {
        // Arrange
        Beer newBeer = new Beer();
        newBeer.setName("new");
        newBeer.setABV(9);
        newBeer.setDescription("new beer");

        when(mockRepository.getBeerById(0))
                .thenReturn(beer);

        // Act
        beerService.updateBeer(0, newBeer);

        // Assert
        verify(mockRepository, Mockito.times(1)).updateBeer(beer);
    }

    @Test
    public void deleteBeer_ShouldBeSuccessful() {
        // Arrange
        int beerId = 0;

        // Act
        beerService.deleteBeer(beerId);

        // Assert
        verify(mockRepository, Mockito.times(1)).removeBeer(0);
    }

    @Test
    public void getBeersByCreator_ShouldBeSuccessful_When_PassedValidUserId() {
        // Arrange
        String name = "test";
        User user = new User();
        user.setUserName(name);
        user.setId(0);
        beer.setCreator(user);
        List<Beer> expectedResult = new ArrayList<>();
        expectedResult.add(beer);
        when(mockRepository.getBeersByCreator(user.getId())).thenReturn(expectedResult);

        // Act
        List<Beer> result = beerService.getBeersByCreator("test");

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getBeersByStyle_ShouldBeSuccessful() {
        // Arrange
        String testStyle = "Light";
        List<Beer> expectedResult = new ArrayList<>();
        expectedResult.add(beer);
        when(beerServiceMock.getBeersByStyle(testStyle)).thenReturn(expectedResult);

        List<Beer> allBeers = new ArrayList<>();
        allBeers.add(beer);
        allBeers.add(new Beer());
        BeerStyle tmpStyle = new BeerStyle();
        tmpStyle.setName("Dark");
        allBeers.get(1).setStyle(tmpStyle);

        when(beerService.getAllBeers()).thenReturn(allBeers);
        //Act
        List<Beer> result = beerService.getBeersByStyle("Light");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void filterBeersByStyle_ShouldBeSuccessful() {
        // Arrange
        String testStyle = "Light";
        List<Beer> expectedResult = new ArrayList<>();
        expectedResult.add(beer);
        when(beerServiceMock.getBeersByStyle(testStyle)).thenReturn(expectedResult);

        List<Beer> allBeers = new ArrayList<>();
        allBeers.add(beer);
        allBeers.add(new Beer());
        BeerStyle tmpStyle = new BeerStyle();
        tmpStyle.setName("Dark");
        allBeers.get(1).setStyle(tmpStyle);

        when(beerService.getAllBeers()).thenReturn(allBeers);
        //Act
        List<Beer> result = beerService.filterBeers("Light", null);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getBeersByCountry_ShouldBeSuccessful() {
        // Arrange
        String testCountry = "Bulgaria";
        List<Beer> expectedResult = new ArrayList<>();
        expectedResult.add(beer);
        when(beerServiceMock.getBeersByCountry(testCountry)).thenReturn(expectedResult);

        List<Beer> allBeers = new ArrayList<>();
        allBeers.add(beer);
        allBeers.add(new Beer());
        Country tmpCountry = new Country();
        tmpCountry.setName("Belgium");
        allBeers.get(1).setOriginCountry(tmpCountry);

        when(beerService.getAllBeers()).thenReturn(allBeers);
        //Act
        List<Beer> result = beerService.getBeersByCountry("Bulgaria");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void filterBeersByCountry_ShouldBeSuccessful() {
        // Arrange
        String testCountry = "Bulgaria";
        List<Beer> expectedResult = new ArrayList<>();
        expectedResult.add(beer);
        when(beerServiceMock.getBeersByCountry(testCountry)).thenReturn(expectedResult);

        List<Beer> allBeers = new ArrayList<>();
        allBeers.add(beer);
        allBeers.add(new Beer());
        Country tmpCountry = new Country();
        tmpCountry.setName("Belgium");
        allBeers.get(1).setOriginCountry(tmpCountry);

        when(beerService.getAllBeers()).thenReturn(allBeers);
        //Act
        List<Beer> result = beerService.filterBeers(null, "Bulgaria");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void sortBeersByABVdesc_ShouldReturnCorrectSortList() {
        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());
        beers.get(0).setABV(3);
        beers.get(1).setABV(4);
        beers.get(2).setABV(5);

        Mockito.when(beerService.getAllBeers()).thenReturn(beers);

        beers = beerService.sortBeers("desc", null, null);
        double[] arr = {beers.get(0).getABV(), beers.get(1).getABV(), beers.get(2).getABV()};
        double[] expected = {5, 4, 3};

        assertEquals(expected[0], arr[0], 1);
        assertEquals(expected[1], arr[1], 1);
        assertEquals(expected[2], arr[2], 1);
    }

    @Test
    public void sortBeersByABVasc_ShouldReturnCorrectSortList() {
        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());
        beers.get(0).setABV(3);
        beers.get(1).setABV(4);
        beers.get(2).setABV(5);

        Mockito.when(beerService.getAllBeers()).thenReturn(beers);

        beers = beerService.sortBeers("asc", null, null);
        double[] arr = {beers.get(0).getABV(), beers.get(1).getABV(), beers.get(2).getABV()};
        double[] expected = {3, 4, 5};

        assertEquals(expected[0], arr[0], 1);
        assertEquals(expected[1], arr[1], 1);
        assertEquals(expected[2], arr[2], 1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBeersByABV_ShouldThrowException_WhenPassedInvalidCriteria() {
        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());
        beers.get(0).setABV(3);
        beers.get(1).setABV(4);
        beers.get(2).setABV(5);

        Mockito.when(beerService.getAllBeers()).thenReturn(beers);

        beers = beerService.sortBeers("des", null, null);
    }

    @Test
    public void sortBeersByAvgRatingDesc_ShouldReturnCorrectSortList() {
        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());
        beers.get(0).setAverageRating(3);
        beers.get(1).setAverageRating(4);
        beers.get(2).setAverageRating(5);

        Mockito.when(beerService.getAllBeers()).thenReturn(beers);

        beers = beerService.sortBeers(null, "desc", null);
        double[] arr = {beers.get(0).getAverageRating(), beers.get(1).getAverageRating(), beers.get(2).getAverageRating()};
        double[] expected = {5, 4, 3};

        assertEquals(expected[0], arr[0], 1);
        assertEquals(expected[1], arr[1], 1);
        assertEquals(expected[2], arr[2], 1);

    }

    @Test
    public void sortBeersByAvgRatingAsc_ShouldReturnCorrectSortList() {
        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());
        beers.get(0).setAverageRating(3);
        beers.get(1).setAverageRating(4);
        beers.get(2).setAverageRating(5);

        Mockito.when(beerService.getAllBeers()).thenReturn(beers);

        beers = beerService.sortBeers(null, "asc", null);
        double[] arr = {beers.get(0).getAverageRating(), beers.get(1).getAverageRating(), beers.get(2).getAverageRating()};
        double[] expected = {3, 4, 5};

        assertEquals(expected[0], arr[0], 1);
        assertEquals(expected[1], arr[1], 1);
        assertEquals(expected[2], arr[2], 1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBeersByAvgRating_ShouldThrowException_WhenPassedInvalidCriteria() {
        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());
        beers.get(0).setAverageRating(3);
        beers.get(1).setAverageRating(4);
        beers.get(2).setAverageRating(5);

        Mockito.when(beerService.getAllBeers()).thenReturn(beers);

        beers = beerService.sortBeers(null, "asce", null);
    }

    @Test
    public void sortBeersAlphabeticallyAsc_ShouldReturnCorrectSortList() {
        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());
        beers.get(0).setName("Heineken");
        beers.get(1).setName("Ariana");
        beers.get(2).setName("Stella");

        Mockito.when(beerService.getAllBeers()).thenReturn(beers);

        beers = beerService.sortBeers(null, null, "asc");
        String[] arr = {beers.get(0).getName(), beers.get(1).getName(), beers.get(2).getName()};
        String[] expected = {"Ariana", "Heineken", "Stella"};

        assertEquals(expected[0], arr[0]);
        assertEquals(expected[1], arr[1]);
        assertEquals(expected[2], arr[2]);

    }

    @Test
    public void sortBeersAlphabeticallyDesc_ShouldReturnCorrectSortList() {
        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());
        beers.get(0).setName("Heineken");
        beers.get(1).setName("Ariana");
        beers.get(2).setName("Stella");

        Mockito.when(beerService.getAllBeers()).thenReturn(beers);

        beers = beerService.sortBeers(null, null, "desc");
        String[] arr = {beers.get(0).getName(), beers.get(1).getName(), beers.get(2).getName()};
        String[] expected = {"Stella", "Heineken", "Ariana"};

        assertEquals(expected[0], arr[0]);
        assertEquals(expected[1], arr[1]);
        assertEquals(expected[2], arr[2]);

    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBeersAlphabetically_ShouldThrowException_WhenPassedInvalidCriteria() {
        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());
        beers.get(0).setName("Heineken");
        beers.get(1).setName("Ariana");
        beers.get(2).setName("Stella");

        Mockito.when(beerService.getAllBeers()).thenReturn(beers);

        beers = beerService.sortBeers(null, null, "descend");
    }


}
