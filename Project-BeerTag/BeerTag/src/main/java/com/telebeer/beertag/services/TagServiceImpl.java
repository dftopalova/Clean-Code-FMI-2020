package com.telebeer.beertag.services;

import com.telebeer.beertag.models.Entities.Beer;
import com.telebeer.beertag.models.Entities.Tag;
import com.telebeer.beertag.repositories.contracts.TagRepository;
import com.telebeer.beertag.services.contracts.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository repository;

    @Autowired
    public TagServiceImpl(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Tag> getAllTags() {
        return repository.getAllTags();
    }

    @Override
    public Tag getTagById(int id) {
        return repository.getTagById(id);
    }

    @Override
    public Tag getTagByName(String tag) {
        return repository.getTagByName(tag);
    }

    @Override
    public Set<Beer> getBeersByTag(String tag) {
        return repository.getBeersByTag(tag);
    }

    @Override
    public Set<Tag> getBeerTags(int beerId) {
        return repository.getBeerTags(beerId);
    }

    @Override
    public void createTag(Tag tag) {
        repository.createTag(tag);
    }

    @Override
    public String addTagToBeer(Tag tag, int beerId) {
       return repository.addTagToBeer(tag, beerId);
    }

    @Override
    public void removeTagFromBeer(int tagId, int beerId) {
        repository.removeTagFromBeer(tagId, beerId);
    }

    @Override
    public void deleteTag(int tagId) {
        repository.deleteTag(tagId);
    }
}
