package com.telebeer.beertag.services;

import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.exceptions.*;
import com.telebeer.beertag.repositories.contracts.BeerRepository;
import com.telebeer.beertag.repositories.contracts.UserRepository;
import com.telebeer.beertag.services.contracts.UserService;
import com.telebeer.beertag.utilities.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_SUCCESSFULLY_CREATED = "{\"message\": \"User with username - %s is successfully created\"} ";
    private static final String USER_SUCCESSFULLY_UPDATED = "{\"message\": \"User with username - %s is successfully updated\"} ";
    private static final String USER_WITH_USERNAME_EXISTS = "{\"message\": \"User with such a username - %s already exists\"}";
    private static final String BEER_NOT_FOUND = "This beer does not exist in the list!";
    public static final String USER_WITH_ID_DOES_NOT_EXIST = "User with id %d does not exist";
    public static final String USERS_WITH_FIRST_NAME_DOES_NOT_EXIST = "Users with first name %s does not exist";
    public static final String USERS_WITH_LAST_NAME_DOES_NOT_EXIST = "Users with last name %s does not exist";
    public static final String NO_ENTERED_FILTER_CRITERIA = "There is no entered any filter criteria";
    public static final String NO_RESULTS_RETURNED = "No results returned";
    public static final String NO_SUCH_SORTING_CRITERIA = "No such sorting criteria";

    private UserRepository repository;
    private BeerRepository beerRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository, @Lazy BeerRepository beerRepository) {
        this.repository = repository;
        this.beerRepository = beerRepository;
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User getById(int id) {
        User result = repository.getById(id);
        if (result == null || !result.isEnabled()) {
            throw new ObjectNotFoundException(
                    String.format(USER_WITH_ID_DOES_NOT_EXIST, id));
        } else {
            return result;
        }
    }

    @Override
    public User getByUsername(String username) {
        return repository.getByUsername(username);
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        List<User> result = repository.getAllByFirstName(firstName);

        if (result == null || result.isEmpty()) {
            throw new NoContentException(
                    String.format(USERS_WITH_FIRST_NAME_DOES_NOT_EXIST, firstName));
        } else {
            return result;
        }
    }

    @Override
    public List<User> getByLastName(String lastName) {
        List<User> result = repository.getAllByLastName(lastName);

        if (result == null || result.isEmpty()) {
            throw new NoContentException(
                    String.format(USERS_WITH_LAST_NAME_DOES_NOT_EXIST, lastName));
        } else {
            return result;
        }
    }


    @Override
    public List<User> getByBothNames(String firstName, String lastName) {

        if (!ValidationHelper.isStringValid(firstName) && !ValidationHelper.isStringValid(lastName)) {
            throw new MalformedRequestException(
                    NO_ENTERED_FILTER_CRITERIA);
        }

        List<User> result;
        if (firstName == null || firstName.isEmpty() && !lastName.isEmpty()) {
            result = repository.getAllByLastName(lastName);

        } else if (lastName == null || lastName.isEmpty() && !firstName.isEmpty()) {
            result = repository.getAllByFirstName(firstName);
        } else {
            result = repository.getAllByFullName(firstName, lastName);
        }
        if (result.isEmpty()) {
            throw new NoContentException(
                    NO_RESULTS_RETURNED);
        } else {
            return result;
        }
    }

    @Override
    public String updateUser(int id, User user) {
        User userToUpdate = getById(id);

        if (!user.getFirstName().isEmpty()) {
            userToUpdate.setFirstName(user.getFirstName());
        }
        if (!user.getLastName().isEmpty()) {
            userToUpdate.setLastName(user.getLastName());
        }
        if (!user.getUserName().isEmpty()) {
            userToUpdate.setUserName(user.getUserName());
        }

        repository.updateUser(userToUpdate);
        return String.format(USER_SUCCESSFULLY_UPDATED, user.getUserName());

    }

    @Override
    public void hardDeleteUser(int id) {
        repository.hardDeleteUser(getById(id));
    }

    @Override
    public void deleteUser(int id) {
        repository.deleteUser(getById(id));
    }

    @Override
    public String createUser(User user) {

        if (userExists(user.getUserName())) {
            throw new CollisionException(String.format(USER_WITH_USERNAME_EXISTS, user.getUserName()));
        }

        repository.createUser(user);

        return String.format(USER_SUCCESSFULLY_CREATED, user.getUserName());
    }

    @Override
    public List<User> sort(String direction, String firstName, String lastName) {
        List<User> result;
        if (firstName != null || lastName != null) {
            result = getByBothNames(firstName, lastName);
        } else {
            result = getAll();
        }

        if (ValidationHelper.isStringValid(direction)) {
            return result;
        }

        switch (direction) {
            case "asc": {
                Collections.sort(result);
                return result;
            }
            case "desc": {
                Collections.sort(result);
                Collections.reverse(result);
                return result;
            }
            default: {
                throw new MalformedRequestException(NO_SUCH_SORTING_CRITERIA);
            }
        }
    }

    @Override
    public Set<Beer> getUserWishlist(String username) {
        User user = getByUsername(username);

        return user.getBeersWishlist();
    }

    @Override
    public Set<Beer> getUserTestedBeers(String username) {
        User user = getByUsername(username);

        return user.getTestedBeers();
    }

    @Override
    public void markBeerAsTested(String username, int beerId) throws BeerAlreadyMarkedException, BeerExistsInOtherListException {

        User user = getByUsername(username);
        Beer beer = beerRepository.getBeerById(beerId);

        if (getUserTestedBeersMap(username).containsKey(beer.getBeerId())) {
            throw new BeerAlreadyMarkedException();
        }

        if (getUserWishlistMap(username).containsKey(beer.getBeerId())) {
            throw new BeerExistsInOtherListException();
        }

        repository.markBeerAsTested(user, beer);
    }

    @Override
    public void markBeerAsWish(String username, int beerId) throws BeerAlreadyMarkedException, BeerExistsInOtherListException {

        User user = getByUsername(username);
        Beer beer = beerRepository.getBeerById(beerId);

        if (getUserWishlistMap(username).containsKey(beer.getBeerId())) {
            throw new BeerAlreadyMarkedException();
        }

        if (getUserTestedBeersMap(username).containsKey(beer.getBeerId())) {
            throw new BeerExistsInOtherListException();
        }

        repository.markBeerAsWish(user, beer);
    }

    @Override
    public void removeBeerFromTestedList(String username, int beerId) {
        User user = getByUsername(username);

        Beer beer = user.getTestedBeers()
                .stream()
                .filter(beer1 -> beer1.getBeerId() == beerId)
                .findFirst()
                .orElseThrow(() -> new NoContentException(BEER_NOT_FOUND));

        repository.removeBeerFromTestedList(user, beer);
    }

    @Override
    public void removeBeerFromWishList(String username, int beerId) {
        User user = getByUsername(username);

        Beer beer = user.getBeersWishlist()
                .stream()
                .filter(beer1 -> beer1.getBeerId() == beerId)
                .findFirst()
                .orElseThrow(() -> new NoContentException(BEER_NOT_FOUND));

        repository.removeBeerFromWishes(user, beer);
    }

    private HashMap<Integer, Beer> getUserTestedBeersMap(String username) {
        User user = getByUsername(username);

        HashMap<Integer, Beer> testedBeers = new HashMap<>();

        for (Beer beer : user.getTestedBeers()) {
            testedBeers.put(beer.getBeerId(), beer);
        }

        return testedBeers;
    }

    private HashMap<Integer, Beer> getUserWishlistMap(String username) {
        User user = getByUsername(username);

        HashMap<Integer, Beer> wishlistMap = new HashMap<>();

        for (Beer beer : user.getBeersWishlist()) {
            wishlistMap.put(beer.getBeerId(), beer);
        }

        return wishlistMap;
    }

    private boolean userExists(String username) {
        return getAll()
                .stream()
                .anyMatch(user -> user.getUserName().equals(username));
    }

}
