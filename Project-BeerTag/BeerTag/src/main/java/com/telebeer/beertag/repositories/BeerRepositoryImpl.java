package com.telebeer.beertag.repositories;

import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.repositories.contracts.BeerRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.telebeer.beertag.utilities.constants.BeerConstants.BEER_SUCCESSFULLY_UPDATED;

@Repository
public class BeerRepositoryImpl implements BeerRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public BeerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Beer> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer where isDeleted=false", Beer.class);

            return query.list();
        }
    }

    @Override
    public Beer getBeerById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Beer.class, id);
        }
    }

    @Override
    public List<Beer> getBeerByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery(
                    "from Beer where name like :name and isDeleted=false", Beer.class);
            query.setParameter("name", name);
            return query.list();
        }
    }

    @Override
    public void createBeer(Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(beer);
        }
    }

    @Override
    public void removeBeer(int id) {
        try (Session session = sessionFactory.openSession()) {
            Beer beerToDelete = getBeerById(id);
            try {
                session.beginTransaction();
                beerToDelete.setDeleted(true);
                session.update(beerToDelete);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public String updateBeer(Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.update(beer);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
        return String.format(BEER_SUCCESSFULLY_UPDATED, beer.getName());
    }

    @Override
    public List<Beer> getBeersByCreator(int creatorId) {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<Beer> query = currentSession.createQuery(
                    "from Beer where creator_id = :creator_id " +
                            "and isDeleted = false ", Beer.class);
            query.setParameter("creator_id", creatorId);
            return query.list();
        }
    }

}

