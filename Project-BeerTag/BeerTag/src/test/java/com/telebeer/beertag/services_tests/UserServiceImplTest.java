package com.telebeer.beertag.services_tests;

import com.telebeer.beertag.exceptions.*;
import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.repositories.contracts.BeerRepository;
import com.telebeer.beertag.services.*;
import com.telebeer.beertag.repositories.contracts.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    private BeerRepository beerRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void findByUsername_Should_ReturnCorrectUser() {

        User temp = new User();
        temp.setUserName("user1");

        Mockito.when(userRepository.getByUsername("user1"))
                .thenReturn(temp);

        User result = userService.getByUsername("user1");

        Assert.assertEquals("user1", result.getUserName());
    }

    @Test
    public void GetAllUsers_ReturnCorrectSizeOfUsers() {

        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        List<User> expected = new ArrayList<>();
        user1.setEnabled(true);
        user2.setEnabled(true);
        user3.setEnabled(true);
        expected.add(user1);
        expected.add(user2);
        expected.add(user3);
        Mockito.when(userRepository.getAll())
                .thenReturn(expected);

        List<User> result = userService.getAll();

        Assert.assertEquals(3, result.size());
        Assert.assertTrue(result.get(0).isEnabled());
        Assert.assertTrue(result.get(1).isEnabled());
        Assert.assertTrue(result.get(2).isEnabled());
    }

    @Test
    public void getUserWhenUserIdGiven_ShouldReturnCorrectUser() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setUserName("user1");

        Mockito.when(userRepository.getById(1))
                .thenReturn(user1);

        User result = userService.getById(1);

        Assert.assertEquals("user1", result.getUserName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void getUserWhenUserIdGiven_ShouldThrowExceptionWhenUserIdIsInvalid() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setUserName("user1");

        Mockito.when(userRepository.getById(1))
                .thenReturn(null);

        User result = userService.getById(1);

        Assert.assertEquals("user1", result.getUserName());
    }

    @Test
    public void getUserWhenUserFirstNameGiven_ShouldReturnCorrectUser() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setFirstName("user1");
        List<User> expected = new ArrayList<>();
        expected.add(user1);

        Mockito.when(userRepository.getAllByFirstName("user1"))
                .thenReturn(expected);

        List<User> result = userService.getByFirstName("user1");

        Assert.assertEquals(expected, result);
    }

    @Test(expected = NoContentException.class)
    public void getUserWhenUserFirstNameGiven_ShouldThrowExceptionWhenFirstNameIsInvalid() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setFirstName("user1");
        List<User> expected = new ArrayList<>();
        expected.add(user1);

        Mockito.when(userRepository.getAllByFirstName("user1"))
                .thenReturn(null);

        List<User> result = userService.getByFirstName("user1");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void getUserWhenUserLastNameGiven_ShouldReturnCorrectUser() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setLastName("user1");
        List<User> expected = new ArrayList<>();
        expected.add(user1);

        Mockito.when(userRepository.getAllByLastName("user1"))
                .thenReturn(expected);

        List<User> result = userService.getByLastName("user1");

        Assert.assertEquals(expected, result);
    }

    @Test(expected = NoContentException.class)
    public void getUserWhenUserLastNameGiven_ShouldThrowExceptionWhenLastNameIsInvalid() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setLastName("user1");
        List<User> expected = new ArrayList<>();
        expected.add(user1);

        Mockito.when(userRepository.getAllByLastName("user1"))
                .thenReturn(null);

        List<User> result = userService.getByLastName("user1");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void getUsersWhenFirstAndLastNameGiven_ShouldReturnCorrectUsers() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setLastName("lname");
        user1.setFirstName("fname");
        List<User> expected = new ArrayList<>();
        expected.add(user1);

        Mockito.when(userRepository.getAllByFullName("fname", "lname"))
                .thenReturn(expected);

        List<User> result = userService.getByBothNames("fname", "lname");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void getUsersWhenFirstNameGiven_ShouldReturnCorrectUsers() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setLastName("lname");
        user1.setFirstName("fname");
        List<User> expected = new ArrayList<>();
        expected.add(user1);

        Mockito.when(userRepository.getAllByFirstName("fname"))
                .thenReturn(expected);

        List<User> result = userService.getByBothNames("fname", "");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void getUsersWhenLastNameGiven_ShouldReturnCorrectUsers() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setLastName("lname");
        user1.setFirstName("fname");
        List<User> expected = new ArrayList<>();
        expected.add(user1);

        Mockito.when(userRepository.getAllByLastName("lname"))
                .thenReturn(expected);

        List<User> result = userService.getByBothNames("", "lname");

        Assert.assertEquals(expected, result);
    }

    @Test(expected = MalformedRequestException.class)
    public void getUsersWhenLastNameGiven_ShouldThrowExceptionWhenNullValuesArePassed() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setLastName("lname");
        user1.setFirstName("fname");
        List<User> expected = new ArrayList<>();
        expected.add(user1);

        List<User> result = userService.getByBothNames(null, null);

    }


    @Test(expected = NoContentException.class)
    public void getUsersWhenLastNameGiven_ShouldThrowExceptionWhenNoUsersExist() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setLastName("lname");
        user1.setFirstName("fname");
        List<User> expected = new ArrayList<>();
        expected.add(user1);

        List<User> result = userService.getByBothNames("asd", "asd");
    }

    @Test
    public void deleteUser_ShouldReturnDisabledUser() {

        User user1 = new User();
        user1.setEnabled(true);
        Mockito.when(userRepository.getById(1))
                .thenReturn(user1);

        userService.deleteUser(1);

        verify(userRepository, Mockito.times(1)).deleteUser(user1);

    }

    @Test
    public void UpdateUser_ShouldUpdateCorrectUpdatedUser() {

        User user = new User();
        user.setId(1);
        user.setEnabled(true);
        user.setUserName("user1");

        Mockito.when(userRepository.getById(1)).thenReturn(user);

        User updUser = new User();
        updUser.setFirstName("fname");
        updUser.setLastName("lname");
        updUser.setUserName("user1");

        userService.updateUser(1, updUser);

        Mockito.verify(userRepository, Mockito.times(1)).getById(1);
        Assert.assertEquals("fname", updUser.getFirstName());
        Assert.assertEquals("lname", updUser.getLastName());
        Assert.assertEquals("user1", updUser.getUserName());
    }


    @Test
    public void addUser_ShouldReturnCorrectCreatedUser() {
        User user1 = new User();
        user1.setEnabled(true);

        userService.createUser(user1);

        verify(userRepository).createUser(user1);
    }

    @Test(expected = CollisionException.class)
    public void addUser_ShouldThrowExceptionWhenUserExists() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setUserName("user1");
        List<User> res = new ArrayList<>();
        res.add(user1);

        Mockito.when(userRepository.getAll()).thenReturn(res);

        userService.createUser(user1);

    }

    @Test
    public void hardDeleteUser_ShouldDeleteCorrectUser() {
        User user1 = new User();
        user1.setId(1);
        user1.setEnabled(true);

        Mockito.when(userRepository.getById(1)).thenReturn(user1);
        userService.hardDeleteUser(1);

        verify(userRepository).hardDeleteUser(user1);
    }

    @Test
    public void markBeerAsTested_ShouldAddBeerToUserTestedList() {
        User user = new User();
        user.setId(1);
        user.setUserName("user");
        user.setEnabled(true);

        Mockito.when(userService.getById(1)).thenReturn(user);
        Mockito.when(userService.getByUsername("user")).thenReturn(user);

        Beer beer = new Beer();
        beer.setBeerId(1);
        beer.setName("name");

        Mockito.when(beerRepository.getById(1)).thenReturn(beer);
        userRepository.markBeerAsTested(user, beer);

        verify(userRepository).markBeerAsTested(user, beer);
    }

    @Test(expected = BeerAlreadyMarkedException.class)
    public void markBeerAsTested_ShouldThrowException_WhenBeerIsAlreadyInTestedList() {
        User user = new User();
        user.setId(1);
        user.setUserName("user");
        user.setEnabled(true);

        Mockito.when(userService.getById(1)).thenReturn(user);
        Mockito.when(userService.getByUsername("user")).thenReturn(user);

        Beer beer = new Beer();
        beer.setBeerId(1);
        beer.setName("name");

        Mockito.when(beerRepository.getById(1)).thenReturn(beer);
        userRepository.markBeerAsTested(user, beer);

        userRepository.markBeerAsTested(user, beer);
    }

    @Test(expected = BeerExistsInOtherListException.class)
    public void markBeerAsTested_ShouldThrowException_WhenBeerIsInOtherListWithBeers() {
        User user = new User();
        user.setId(1);
        user.setEnabled(true);
        user.setUserName("username");

        Mockito.when(userService.getById(1)).thenReturn(user);
        Mockito.when(userService.getByUsername("user")).thenReturn(user);

        Beer beer = new Beer();
        beer.setBeerId(1);
        beer.setName("name");

        Mockito.when(beerRepository.getById(1)).thenReturn(beer);
        userRepository.markBeerAsWish(user, beer);

        userRepository.markBeerAsTested(user, beer);
    }

    @Test
    public void markBeerAsWish_ShouldAddBeerToUserWishList() {
        User user = new User();
        user.setId(1);
        user.setEnabled(true);
        user.setUserName("user");

        Mockito.when(userService.getById(1)).thenReturn(user);
        Mockito.when(userService.getByUsername("user")).thenReturn(user);

        Beer beer = new Beer();
        beer.setBeerId(1);
        beer.setName("name");

        Mockito.when(beerRepository.getById(1)).thenReturn(beer);
        userRepository.markBeerAsWish(user, beer);

        verify(userRepository).markBeerAsWish(user, beer);
    }

    @Test(expected = BeerAlreadyMarkedException.class)
    public void markBeerAsWish_ShouldThrowException_WheBeerIsAlreadyInTheWishList() {
        User user = new User();
        user.setId(1);
        user.setUserName("user");
        user.setEnabled(true);

        Mockito.when(userService.getById(1)).thenReturn(user);
        Mockito.when(userService.getByUsername("user")).thenReturn(user);

        Beer beer = new Beer();
        beer.setBeerId(1);
        beer.setName("name");

        Mockito.when(beerRepository.getById(1)).thenReturn(beer);
        userRepository.markBeerAsWish(user, beer);

        userRepository.markBeerAsWish(user, beer);
    }

    @Test(expected = BeerExistsInOtherListException.class)
    public void markBeerAsWish_ShouldThrowException_WhenBeerIsInOtherListWithBeers() {
        User user = new User();
        user.setId(1);
        user.setEnabled(true);
        user.setUserName("user");

        Mockito.when(userService.getById(1)).thenReturn(user);
        Mockito.when(userService.getByUsername("user")).thenReturn(user);

        Beer beer = new Beer();
        beer.setBeerId(1);
        beer.setName("name");

        Mockito.when(beerRepository.getById(1)).thenReturn(beer);
        userRepository.markBeerAsTested(user, beer);

        userRepository.markBeerAsWish(user, beer);
    }

    @Test
    public void removeBeerFromTestedBeers_ShouldRemoveTheCorrectBeer() {
        User user = new User();
        user.setId(1);
        user.setEnabled(true);
        user.setUserName("user");

        Beer beer = new Beer();
        beer.setBeerId(1);
        beer.setName("name");

        Set<Beer> beersList = new HashSet<>();
        beersList.add(beer);
        user.setTestedBeers(beersList);

        Mockito.when(userService.getById(1)).thenReturn(user);
        Mockito.when(userService.getByUsername("user")).thenReturn(user);
        userRepository.removeBeerFromTestedList(user, beer);

        verify(userRepository).removeBeerFromTestedList(user, beer);
    }

    @Test(expected = NoContentException.class)
    public void removeBeerFromTestedBeers_ShouldThrowException_WhenBeerNotFound() {
        User user = new User();
        user.setId(1);
        user.setEnabled(true);
        user.setUserName("user");
        user.setTestedBeers(new HashSet<>());

        Mockito.when(userService.getById(1)).thenReturn(user);
        Mockito.when(userService.getByUsername("user")).thenReturn(user);
        userService.removeBeerFromTestedList("user", 1);
    }

    @Test
    public void removeBeerFromWishList_ShouldRemoveTheCorrectBeer() {
        User user = new User();
        user.setId(1);
        user.setEnabled(true);
        user.setUserName("user");

        Beer beer = new Beer();
        beer.setBeerId(1);
        beer.setName("name");

        Set<Beer> beersList = new HashSet<>();
        beersList.add(beer);
        user.setBeersWishlist(beersList);

        Mockito.when(userService.getById(1)).thenReturn(user);
        Mockito.when(userService.getByUsername("user")).thenReturn(user);
        userRepository.removeBeerFromWishes(user, beer);

        verify(userRepository).removeBeerFromWishes(user, beer);
    }

    @Test(expected = NoContentException.class)
    public void removeBeerFromWishList_ShouldThrowException_WhenBeerNotFound() {
        User user = new User();
        user.setId(1);
        user.setEnabled(true);
        user.setUserName("user");
        user.setBeersWishlist(new HashSet<>());

        Mockito.when(userService.getById(1)).thenReturn(user);
        Mockito.when(userService.getByUsername("user")).thenReturn(user);
        userService.removeBeerFromWishList("user", 1);
    }

}
