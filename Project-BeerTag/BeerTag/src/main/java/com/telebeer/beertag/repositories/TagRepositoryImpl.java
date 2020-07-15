package com.telebeer.beertag.repositories;

import com.telebeer.beertag.models.entities.Beer;
import com.telebeer.beertag.models.entities.Tag;
import com.telebeer.beertag.repositories.contracts.BeerRepository;
import com.telebeer.beertag.repositories.contracts.TagRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.telebeer.beertag.exceptions.*;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TagRepositoryImpl implements TagRepository {

    private static final String TAG_NOT_FOUND_MESSAGE = "Tag not found";
    private static final String TAG_SUCCESSFULLY_ADDED =
            "{\"message\": \"Tag- %s successfully added\"}";
    private static final String TAG_FAIL_ADDED =
            "{\"message\": \"Tag- %s adding failed\"}";


    private SessionFactory sessionFactory;
    private BeerRepository beerRepository;

    @Autowired
    public TagRepositoryImpl(SessionFactory sessionFactory, @Lazy BeerRepository beerRepository) {
        this.sessionFactory = sessionFactory;
        this.beerRepository = beerRepository;
    }

    @Override
    public Map<String, Tag> getAllTags() {
        Map<String, Tag> tags = new HashMap<String, Tag>();
        try (Session session = sessionFactory.openSession()) {
            Query<Tag> query = session
                    .createQuery("from Tag where isDeleted = false ", Tag.class);
            List<Tag> tagsList = query.list();

            for (Tag tag : tagsList) {
                tags.put(tag.getTagBody().toLowerCase().trim(), tag);
            }

            return tags;
        }
    }

    @Override
    public Tag getTagById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Tag.class, id);
        }
    }

    @Override
    public Tag getTagByName(String tag) {
        try (Session session = sessionFactory.openSession()) {
            Query<Tag> query = session
                    .createQuery("from Tag where tag_body = :tag and " +
                            "isDeleted = false ", Tag.class);
            query.setParameter("tag", tag);
            return query.getSingleResult();
        }
    }

    @Override
    public Set<Beer> getBeersByTag(String tagName) {
        Tag tag = getTagByName(tagName);

        return tag.getBeers();
    }

    @Override
    public Set<Tag> getBeerTags(int beerId) {
        Beer beer = beerRepository.getBeerById(beerId);
        Set<Tag> tags = beer.getTags()
                .stream()
                .filter(tag -> !tag.isDeleted())
                .collect(Collectors.toSet());
        return tags;
    }

    @Override
    public void createTag(Tag tag) {
        try (Session session = sessionFactory.openSession()) {
            session.save(tag);
        }
    }

    @Override
    public String addTagToBeer(Tag tag, int beerId) {
        try (Session session = sessionFactory.openSession()) {

            try {
                session.beginTransaction();
                if (!getAllTags().containsKey(tag.getTagBody())) {
                    createTag(tag);
                }

                Beer beer = beerRepository.getBeerById(beerId);

                Tag tagToAdd = getTagByName(tag.getTagBody().toLowerCase().trim());
                beer.getTags().add(tagToAdd);
                tagToAdd.getBeers().add(beer);

                session.update(tagToAdd);
                session.update(beer);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
        return String.format(
                TAG_SUCCESSFULLY_ADDED, tag.getTagBody()
        );
    }

    @Override
    public void removeTagFromBeer(int tagId, int beerId) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                Beer beer = beerRepository.getBeerById(beerId);

                Tag tagToRemove = beer.getTags().stream().filter(tag -> tag.getTagId() == tagId).findFirst()
                        .orElseThrow(() -> new NoContentException(TAG_NOT_FOUND_MESSAGE));

                beer.getTags().remove(tagToRemove);

                tagToRemove.getBeers().remove(beer);

                session.update(tagToRemove);
                session.update(beer);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void deleteTag(int tagId) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                Tag tempTag = getTagById(tagId);
                tempTag.setDeleted(true);

                session.update(tempTag);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
    }
}
