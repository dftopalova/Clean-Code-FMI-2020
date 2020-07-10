package com.telebeer.beertag.services;

import com.telebeer.beertag.models.entities.User;
import com.telebeer.beertag.repositories.contracts.BeerRepository;
import com.telebeer.beertag.repositories.contracts.UserRepository;
import com.telebeer.beertag.services.contracts.UserService;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_SUCCESSFULLY_CREATED = "{\"message\": \"User with username - %s is successfully created\"} ";
    private static final String USER_SUCCESSFULLY_UPDATED = "{\"message\": \"User with username - %s is successfully updated\"} ";
    private static final String USER_WITH_USERNAME_EXISTS = "{\"message\": \"User with the username - %s already exists\"}";
    private static final String BEER_DOES_NOT_EXIST_IN_THE_LIST_MESSAGE = "This beer does not exist in the list!";

    private UserRepository repository;
    private BeerRepository beerRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository, @Lazy BeerRepository beerRepository) {
        this.repository = repository;
        this.beerRepository = beerRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @Override
    public User getById(int id) {
        User result = repository.getById(id);
        if (result == null || !result.isEnabled()) {
            throw new ObjectNotFoundException(
                    String.format("User with id %d does not exist", id));
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
        List<User> result = repository.getByFirstName(firstName);

        if (result == null || result.isEmpty()) {
            throw new NoContentException(
                    String.format("Users with first name %s does not exist", firstName));
        } else {
            return result;
        }
    }

    @Override
    public List<User> getByLastName(String lastName) {
        List<User> result = repository.getByLastName(lastName);

        if (result == null || result.isEmpty()) {
            throw new NoContentException(
                    String.format("Users with last name %s does not exist", lastName));
        } else {
            return result;
        }
    }


    @Override
    public List<User> getByBothNames(String firstName, String lastName) {

        if (firstName == null && lastName == null) {
            throw new MalformedRequestException(
                    "You haven't entered any filter criteria");
        }
        List<User> result = new ArrayList<>();
        if (firstName == null || firstName.isEmpty() && !lastName.isEmpty()) {
            result = repository.getByLastName(lastName);

        } else if (lastName == null || lastName.isEmpty() && !firstName.isEmpty()) {
            result = repository.getByFirstName(firstName);
        } else {
            result = repository.getByBothNames(firstName, lastName);
        }
        if (result.isEmpty()) {
            throw new NoContentException(
                    "Your filter criteria returned no results");
        } else {
            return result;
        }
    }

    @Override
    public String updateProfileInfo(int id, User user) {
        User orig = getById(id);

        if (!user.getFirstName().isEmpty()) {
            orig.setFirstName(user.getFirstName());
        }
        if (!user.getLastName().isEmpty()) {
            orig.setLastName(user.getLastName());
        }
        if (!user.getUserName().isEmpty()) {
            orig.setUserName(user.getUserName());
        }

        repository.updateUser(orig);
        return String.format(USER_SUCCESSFULLY_UPDATED, user.getUserName());

    }

    @Override
    public void hardDeleteUser(int id) {
        User temp = getById(id);
        repository.hardDeleteUser(temp);
    }


    @Override
    public void deleteUser(int id) {

        repository.deleteUser(getById(id));
    }

    @Override
    public String addUser(User user) {

        if (userExists(user.getUserName())) {
            throw new CollisionException(String.format(USER_WITH_USERNAME_EXISTS, user.getUserName()));
        }

        repository.addUser(user);

        return String.format(USER_SUCCESSFULLY_CREATED, user.getUserName());
    }

    @Override
    public List<User> sort(String direction, String firstName, String lastName) {
        List<User> temp;
        if (firstName != null || lastName != null) {
            temp = getByBothNames(firstName, lastName);
        } else {
            temp = getAllUsers();
        }

        if (direction == null || direction.isEmpty()) {
            return getAllUsers();
        }

        if (direction.equals("asc")) {
            Collections.sort(temp);
            return temp;
        } else if (direction.equals("desc")) {
            Collections.sort(temp);
            Collections.reverse(temp);
            return temp;
        } else {
            throw new MalformedRequestException(
                    "No such sorting criteria");
        }
    }

    @Override
    public Set<Beer> getUserWishlist(String username) {
        User user = getByUsername(username);

        Set<Beer> wishlist = user.getBeersWishlist();

        return wishlist;
    }

    @Override
    public Set<Beer> getUserDrankBeers(String username) {
        User user = getByUsername(username);

        Set<Beer> drankBeers = user.getDrunkBeers();

        return drankBeers;
    }

    private HashMap<Integer, Beer> getUserDrunkBeersMap(String username) {
        User user = getByUsername(username);

        HashMap<Integer, Beer> drunkBeers = new HashMap<>();

        for (Beer beer : user.getDrunkBeers()) {
            drunkBeers.put(beer.getBeerId(), beer);
        }

        return drunkBeers;
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
        return getAllUsers().stream()
                .anyMatch(user -> user.getUserName().equals(username));

    }

}
