package com.telebeer.beertag.controllers.RESTControllers;

import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.services.contracts.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/tags")
public class TagRestController {
    private TagService service;

    @Autowired
    public TagRestController(TagService service) {
        this.service = service;
    }

    @GetMapping
    public Map<String, Tag> getAllTags() {
        return service.getAll();
    }

    @PostMapping
    public void createTag(@Valid @RequestBody Tag tag) {
        service.createTag(tag);
    }

    @GetMapping("/beers")
    public Set<Beer> getBeersByTag(@RequestParam String tag) {
        return service.getBeersByTag(tag);
    }

    @GetMapping("/")
    public Set<Tag> getBeerTags(@RequestParam int beerId) {
        return service.getBeerTags(beerId);
    }

    @PostMapping("/tagBeer")
    public String addTagToBeer(@Valid @RequestBody Tag tag, @RequestParam int beerId) {
        return service.addTagToBeer(tag, beerId);
    }

    @DeleteMapping("/{tagId}/beers/{beerId}")
    public void removeTagFromBeer(@PathVariable int tagId, @PathVariable int beerId) {
        service.removeTagFromBeer(tagId, beerId);
    }

    @DeleteMapping("/{tagId}")
    public void deleteTag(@PathVariable int tagId) {
        service.deleteTag(tagId);
    }
}
