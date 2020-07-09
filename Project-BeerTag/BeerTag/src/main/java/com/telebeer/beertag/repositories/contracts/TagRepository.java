package com.telebeer.beertag.repositories.contracts;

import com.telebeer.beertag.models.Entities.Beer;
import com.telebeer.beertag.models.Entities.Tag;

import java.util.Map;
import java.util.Set;

public interface TagRepository {
    Map<String, Tag> getAllTags();

    Tag getTagById(int id);

    Tag getTagByName(String tag);

    Set<Beer> getBeersByTag(String tag);

    Set<Tag> getBeerTags(int beerId);

    void createTag(Tag tag);

    String addTagToBeer(Tag tag, int beerId);

    void removeTagFromBeer(int tagId, int beerId);

    void deleteTag(int tagId);


}
