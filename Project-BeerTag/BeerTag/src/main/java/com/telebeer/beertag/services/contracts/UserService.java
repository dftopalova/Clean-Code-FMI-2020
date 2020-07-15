package com.telebeer.beertag.services.contracts;

import com.telebeer.beertag.models.dtos.UserDTO;
import com.telebeer.beertag.models.entities.Beer;
import com.telebeer.beertag.models.entities.User;
import com.telebeer.beertag.exceptions.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();

    User getById(int id);

    User getByUsername(String username);

    List<User> getByFirstName(String name);

    List<User> getByLastName(String name);

    List<User> getByBothNames(String firstName, String lastName);

    String updateProfileInfo(int id, User user);

    void hardDeleteUser(int id);

    void deleteUser(int id);

    String addUser(User user);

    List<User> sort(String direction, String firstName, String lastName);

    Set<Beer> getUserDrankBeers(String username);

    Set<Beer> getUserWishlist(String username);

    void markBeerAsDranked(String username, int beerId) throws BeerAlreadyMarkedException, BeerExistsInOtherListException;

    void markBeerAsWish(String username, int beerId) throws BeerAlreadyMarkedException, BeerExistsInOtherListException;

    void removeBeerFromDrankList(String username, int beerId);

    void removeBeerFromWishes(String username, int beerId);

    User migrateFromDTOToUserEntity(UserDTO userDTO) throws IOException;
}
