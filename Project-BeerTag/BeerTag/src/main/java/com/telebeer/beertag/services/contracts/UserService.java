package com.telebeer.beertag.services.contracts;

import com.telebeer.beertag.models.dtos.UserDTO;
import com.telebeer.beertag.models.entities.Beer;
import com.telebeer.beertag.models.entities.User;
import com.telebeer.beertag.exceptions.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAll();

    User getById(int id);

    User getByUsername(String username);

    List<User> getByFirstName(String name);

    List<User> getByLastName(String name);

    List<User> getByBothNames(String firstName, String lastName);

    String createUser(User user);

    String updateUser(int id, User user);

    void hardDeleteUser(int id);

    void deleteUser(int id);

    List<User> sort(String direction, String firstName, String lastName);

    Set<Beer> getUserTestedBeers(String username);

    Set<Beer> getUserWishlist(String username);

    void markBeerAsTested(String username, int beerId) throws BeerAlreadyMarkedException, BeerExistsInOtherListException;

    void markBeerAsWish(String username, int beerId) throws BeerAlreadyMarkedException, BeerExistsInOtherListException;

    void removeBeerFromTestedList(String username, int beerId);

    void removeBeerFromWishList(String username, int beerId);

}
