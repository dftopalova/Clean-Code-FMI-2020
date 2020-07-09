package com.telebeer.beertag.models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telebeer.beertag.models.Entities.Beer;

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
    @Column(name = "tag_id")
    private int tagId;

    @Column(name = "tag_body")
    private String tagBody;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
    @JsonIgnore
    private Set<Beer> beers = new HashSet<>();

    public Set<Beer> getBeers() {
        return beers;
    }

    public void setBeers(Set<Beer> beers) {
        this.beers = beers;
    }

    public Tag() {
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagBody() {
        return tagBody;
    }

    public void setTagBody(String tagBody) {
        this.tagBody = tagBody;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
