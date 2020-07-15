package com.telebeer.beertag.services_tests;

import com.telebeer.beertag.exceptions.*;
import com.telebeer.beertag.models.entities.*;
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
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

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
        Mockito.when(userRepository.getAllUsers())
                .thenReturn(expected);

        List<User> result = userService.getAllUsers();

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

        Mockito.when(userRepository.getByFirstName("user1"))
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

        Mockito.when(userRepository.getByFirstName("user1"))
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

        Mockito.when(userRepository.getByLastName("user1"))
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

        Mockito.when(userRepository.getByLastName("user1"))
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

        Mockito.when(userRepository.getByBothNames("fname", "lname"))
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

        Mockito.when(userRepository.getByFirstName("fname"))
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

        Mockito.when(userRepository.getByLastName("lname"))
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

        List<User> result = userService.getByBothNames(null,null);

    }


    @Test(expected = NoContentException.class)
    public void getUsersWhenLastNameGiven_ShouldThrowExceptionWhenNoUsersExist() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setLastName("lname");
        user1.setFirstName("fname");
        List<User> expected = new ArrayList<>();
        expected.add(user1);

        List<User> result = userService.getByBothNames("asd","asd");
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

        userService.updateProfileInfo(1,updUser);

        Mockito.verify(userRepository, Mockito.times(1)).getById(1);
        Assert.assertEquals("fname", updUser.getFirstName());
        Assert.assertEquals("lname", updUser.getLastName());
        Assert.assertEquals("user1", updUser.getUserName());
    }


    @Test
    public void addUser_ShouldReturnCorrectCreatedUser() {
        User user1 = new User();
        user1.setEnabled(true);

        userService.addUser(user1);

        verify(userRepository).addUser(user1);
    }

    @Test(expected = CollisionException.class)
    public void addUser_ShouldThrowExceptionWhenUserExists() {
        User user1 = new User();
        user1.setEnabled(true);
        user1.setUserName("user1");
        List<User> res = new ArrayList<>();
        res.add(user1);

        Mockito.when(userRepository.getAllUsers()).thenReturn(res);

        userService.addUser(user1);

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

}
