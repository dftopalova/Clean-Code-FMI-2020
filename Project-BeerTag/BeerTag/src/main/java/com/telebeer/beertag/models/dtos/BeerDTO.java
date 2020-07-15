package com.telebeer.beertag.models.dtos;

import org.springframework.web.multipart.MultipartFile;

public class BeerDTO {

    private String beerName;
    private String creatorUsername;
    private double ABV;
    private String description;
    private String brewery;
    private String originCountry;
    private String beerStyle;
    private MultipartFile beerPicture;

    public BeerDTO() {
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public double getABV() {
        return ABV;
    }

    public void setABV(double ABV) {
        this.ABV = ABV;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public MultipartFile getBeerPicture() {
        return beerPicture;
    }

    public void setBeerPicture(MultipartFile beerPicture) {
        this.beerPicture = beerPicture;
    }
}
