package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.entities.*;

import java.util.List;

public interface UserRepository {

    List<User> getAllByFirstName(String firstName);

    List<User> getAllByLastName(String lastName);

    List<User> getAllByFullName(String firstName, String lastName);

    List<User> getAll();

    User getById(int id);

    User getByUsername(String username);

    void hardDeleteUser(User user);

    void deleteUser(User user);

    void createUser(User user);

    void updateUser(User user);

    void markBeerAsTested(User user, Beer beer);

    void markBeerAsWish(User user, Beer beer);

    void removeBeerFromTestedList(User user, Beer beer);

    void removeBeerFromWishes(User user, Beer beer);

}
