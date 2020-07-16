package com.telebeer.beertag.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    @PositiveOrZero
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "body")
    private String body;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
    @JsonIgnore
    private Set<Beer> beers = new HashSet<>();

    public Tag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int tagId) {
        this.id = tagId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String tagBody) {
        this.body = tagBody;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<Beer> getBeers() {
        return beers;
    }

    public void setBeers(Set<Beer> beers) {
        this.beers = beers;
    }
}
