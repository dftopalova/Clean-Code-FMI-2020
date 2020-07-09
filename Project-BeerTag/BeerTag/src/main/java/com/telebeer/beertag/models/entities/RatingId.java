package com.telebeer.beertag.models.Entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingId implements Serializable {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "beer_id")
    private int beerId;

    public RatingId() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingId)) return false;
        RatingId that = (RatingId) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getBeerId(), that.getBeerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getBeerId());
    }
}
