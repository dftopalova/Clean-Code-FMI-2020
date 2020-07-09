package com.telebeer.beertag.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @EmbeddedId
    private RatingId ratingId;

    @MapsId("userId")
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("beerId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "beer_id")
    @JsonIgnore
    private Beer beer;

    @Column(name = "rating")
    private double rating;

    public Rating() {
    }

    public RatingId getRatingId() {
        return ratingId;
    }

    public void setRatingId(RatingId ratingId) {
        this.ratingId = ratingId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }
}
