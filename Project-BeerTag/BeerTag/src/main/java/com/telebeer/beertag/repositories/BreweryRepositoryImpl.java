package com.telebeer.beertag.repositories;

import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.repositories.contracts.BreweryRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.telebeer.beertag.utilities.constants.BreweryConstants.BREWERY_SUCCESSFULLY_CREATED;

@Repository
public class BreweryRepositoryImpl implements BreweryRepository {

    private SessionFactory sessionFactory;

   @Autowired
    public BreweryRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Brewery> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Brewery> query = session
                    .createQuery("from Brewery where isDeleted = false", Brewery.class);
            return query.list();
        }
    }

    @Override
    public String createBrewery(Brewery brewery) {
        try (Session session = sessionFactory.openSession()) {
            session.save(brewery);
        }
        return BREWERY_SUCCESSFULLY_CREATED;
    }

    @Override
    public Brewery getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Brewery.class, id);
        }
    }

    @Override
    public Brewery getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Brewery> query = session
                    .createQuery("from Brewery where isDeleted = false and name = :name",
                            Brewery.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        }
    }

    @Override
    public void deleteBrewery(int id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                Brewery tempBrew = getById(id);
                tempBrew.setDeleted(true);

                session.update(tempBrew);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
    }
}
