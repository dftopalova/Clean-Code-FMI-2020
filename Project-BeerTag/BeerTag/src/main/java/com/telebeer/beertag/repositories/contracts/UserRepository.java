package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.entities.*;

import java.util.List;

public interface UserRepository {

    List<User> getByFirstName(String firstName);

    List<User> getByLastName(String lastName);

    List<User> getByBothNames(String firstName, String lastName);

    List<User> getAllUsers();

    User getById(int id);

    User getByUsername(String username);

    void hardDeleteUser(User user);

    void deleteUser(User user);

    void addUser(User user);

    void updateUser(User user);

    void markBeerAsDranked(User user, Beer beer);

    void markBeerAsWish(User user, Beer beer);

    void removeBeerfromDrankList(User user, Beer beer);

    void removeBeerFromWishes(User user, Beer beer);

}
