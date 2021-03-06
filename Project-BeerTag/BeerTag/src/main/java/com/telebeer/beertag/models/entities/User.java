package com.telebeer.beertag.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.telebeer.beertag.utilities.constants.UserConstants.*;

@Entity
@Table(name = "users")
public class User implements Comparable<User> {

    @PositiveOrZero
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = FIRST_NAME_MIN_LENGTH, max = FIRST_NAME_MAX_LENGTH, message = FIRST_NAME_LENGTH_CONSTRAINT_MESSAGE)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = LAST_NAME_MIN_LENGTH, max = LAST_NAME_MAX_LENGTH, message = LAST_NAME_CONSTRAINT_MESSAGE)
    @Column(name = "last_name")
    private String lastName;

    @Size(min = USERNAME_MIN_LENGTH, max = USERNAME_MAX_LENGTH, message = USERNAME_LENGTH_CONSTRAINT_MESSAGE)
    @Column(name = "username")
    private String userName;

    @Size(min = PASSWORD_MIN_LENGTH, message = PASSWORD_LENGTH_CONSTRAINT_MESSAGE)
    @Column(name = "password")
    private String password;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_testedBeers",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "beer_id")
    )
    @JsonIgnore
    private Set<Beer> testedBeers = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_wishlist",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "beer_id")
    )

    @JsonIgnore
    private Set<Beer> beersWishlist = new HashSet<>();

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean state) {
        enabled = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPicture() {
        return picture;
    }

    public String printPicture() {
        return new String(Base64.encodeBase64(this.picture));
    }

    public void setPicture(MultipartFile picture) throws IOException {
        this.picture = picture.getBytes();
    }

    public void updatePicture(byte[] newPicture) {
        this.picture = newPicture;
    }

    public Set<Beer> getTestedBeers() {
        return testedBeers;
    }

    public void setTestedBeers(Set<Beer> drunkBeers) {
        this.testedBeers = drunkBeers;
    }

    public Set<Beer> getBeersWishlist() {
        return beersWishlist;
    }

    public void setBeersWishlist(Set<Beer> beersWishlist) {
        this.beersWishlist = beersWishlist;
    }

    public int compareTo(User user) {
        int result = this.firstName.compareTo(user.getFirstName());
        if (result == 0) {
            result = this.lastName.compareTo(user.getLastName());
        }
        if (result == 0) {
            result = Integer.compare(this.id, user.getId());
        }
        return result;
    }

}
