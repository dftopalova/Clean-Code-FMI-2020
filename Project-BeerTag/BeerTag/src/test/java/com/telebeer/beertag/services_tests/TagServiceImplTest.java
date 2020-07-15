package com.telebeer.beertag.services_tests;

import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.services.*;
import com.telebeer.beertag.repositories.contracts.TagRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

public class TagServiceImplTest {
    private static final String TAG_SUCCESSFULLY_ADDED =
            "{\"message\": \"Tag- %s successfully added\"}";

    @Mock
    TagRepository repository;
    @InjectMocks
    TagServiceImpl tagServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTags() {
        Map<String, Tag> temp = new HashMap<>();
        temp.put("tag", new Tag());

        when(repository.getAllTags()).thenReturn(temp);

        Map<String, Tag> result = tagServiceImpl.getAllTags();
        Assert.assertEquals(temp ,result);
    }

    @Test
    public void testGetTagById() {
        Tag temp = new Tag();
        when(repository.getTagById(1)).thenReturn(temp);

        Tag result = tagServiceImpl.getTagById(1);
        Assert.assertEquals(temp, result);
    }

    @Test
    public void testGetTagByName() {
        Tag temp = new Tag();
        temp.setTagBody("tag");
         when(repository.getTagByName(anyString())).thenReturn(temp);

        Tag result = tagServiceImpl.getTagByName("tag");
        Assert.assertEquals(temp, result);
    }

    @Test
    public void testGetBeersByTag() {
        Tag temp = new Tag();
        temp.setTagBody("tag");
        Beer beer = new Beer();
        Set<Beer> tempSet = new HashSet<>();
        tempSet.add(beer);
        temp.setBeers(tempSet);
        when(repository.getBeersByTag("tag")).thenReturn(tempSet);

        Set<Beer> result = tagServiceImpl.getBeersByTag("tag");
        Assert.assertEquals(tempSet, result);
    }

    @Test
    public void testGetBeerTags() {
        Beer beer = new Beer();
        Tag temp = new Tag();
        temp.setTagBody("tag");
        Set<Tag> tempSet = new HashSet<>();
        tempSet.add(temp);

        beer.setTags(tempSet);
        when(repository.getBeerTags(0)).thenReturn(tempSet);

        Set<Tag> result = tagServiceImpl.getBeerTags(0);
        Assert.assertEquals(tempSet, result);
    }

    @Test
    public void testCreateTag() {
        Map<String, Tag> temp = new HashMap<>();
        temp.put("tag", new Tag());

        when(repository.getAllTags()).thenReturn(temp);

        Map<String, Tag> result = tagServiceImpl.getAllTags();
        Assert.assertEquals(temp.size() ,result.size());
    }

    @Test
    public void testAddTagToBeer() {
        Beer beer = new Beer();
        Tag temp = new Tag();
        temp.setTagBody("tag");
        Set<Tag> tempSet = new HashSet<>();
        tempSet.add(temp);

        beer.setTags(tempSet);
        when(repository.addTagToBeer(temp,0)).thenReturn(TAG_SUCCESSFULLY_ADDED);

        Set<Tag> result = tagServiceImpl.getBeerTags(0);
        Assert.assertEquals(TAG_SUCCESSFULLY_ADDED, repository.addTagToBeer(temp,0));
    }

    @Test
    public void testRemoveTagFromBeer() {
        Beer beer = new Beer();
        Tag temp = new Tag();
        temp.setTagBody("tag");
        Set<Tag> tempSet = new HashSet<>();
        tempSet.add(temp);

        beer.setTags(tempSet);
        when(repository.getBeerTags(0)).thenReturn(tempSet);

        Set<Tag> result = tagServiceImpl.getBeerTags(0);
        Assert.assertEquals(tempSet, result);    }

    @Test
    public void testDeleteTag()  {
        tagServiceImpl.deleteTag(0);
    }
}
