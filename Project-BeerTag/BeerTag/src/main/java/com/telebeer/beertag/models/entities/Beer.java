package com.telebeer.beertag.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.Formula;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "beers")
public class Beer implements Comparable<Beer> {

    @PositiveOrZero
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 3, max = 15, message = "Beer name must be between 3 and 15 symbols!")
    @Column(name = "name")
    private String beerName;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @JsonIgnore
    private User creator;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "brewery_id")
    private Brewery brewery;

    @Column(name = "ABV")
    private double ABV;

    @ManyToOne
    @JoinColumn(name = "origin_country_id")
    private Country originCountry;

    @OneToOne
    @JoinColumn(name = "beer_style_id")
    private BeerStyle style;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "beer_tags",
            joinColumns = @JoinColumn(name = "beer_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnore
    private Set<Tag> tags = new HashSet<>();

    @Column(name = "avg_rating")
    @Formula("(select ifnull(round(avg(r.rating),2),0)\n" +
            "from ratings r\n" +
            "where r.beer_id = beer_id)")
    private double averageRating;

    @OneToMany(mappedBy = "beer", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Rating> ratings = new HashSet<>();

    @Lob
    @Column(name = "picture", length = 16777215)
    private byte[] picture;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    public Beer() {
    }

    public int getBeerId() {
        return id;
    }

    public void setBeerId(int beerId) {
        this.id = beerId;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }

    public double getABV() {
        return ABV;
    }

    public void setABV(double ABV) {
        this.ABV = ABV;
    }

    public Country getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(Country originCountry) {
        this.originCountry = originCountry;
    }

    public BeerStyle getStyle() {
        return style;
    }

    public void setStyle(BeerStyle style) {
        this.style = style;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public byte[] getBeerPicture() {
        return picture;
    }

    public String printBeerPicture() {
        return new String(Base64.encodeBase64(this.picture));
    }

    public void setBeerPicture(MultipartFile beerPicture) throws IOException {
        this.picture = beerPicture.getBytes();
    }

    public void updateBeerPicture(byte[] newPicture) {
        this.picture = newPicture;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    public int compareTo(Beer o) {
        int result = Double.compare(this.getABV(), o.getABV());
        if (result == 0) {
            result = Double.compare(this.getAverageRating(), o.getAverageRating());
        }
        if (result == 0) {
            result = this.getBeerName().compareTo(o.getBeerName());
        }

        return result;
    }
}
