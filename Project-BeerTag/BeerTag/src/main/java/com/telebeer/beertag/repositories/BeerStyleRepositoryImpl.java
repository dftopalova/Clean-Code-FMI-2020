package com.telebeer.beertag.repositories;

import com.telebeer.beertag.models.Entities.BeerStyle;
import com.telebeer.beertag.models.Entities.Brewery;
import com.telebeer.beertag.repositories.contracts.BeerStyleRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BeerStyleRepositoryImpl implements BeerStyleRepository {
    private static final String STYLE_SUCCESSFULLY_ADDED =
            "{\"message\": \"Beer style- %s successfully added\"}";

    private SessionFactory sessionFactory;

    @Autowired
    public BeerStyleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String addBeerStyle(BeerStyle beerStyle) {
        try (Session session = sessionFactory.openSession()) {
            session.save(beerStyle);
        }
        return String.format(STYLE_SUCCESSFULLY_ADDED, beerStyle.getName());
    }


    @Override
    public List<BeerStyle> getAllBeerStyles() {
        try (Session session = sessionFactory.openSession()) {
            Query<BeerStyle>  query = session
                    .createQuery("from BeerStyle where isDeleted = false", BeerStyle.class);
            return query.list();
        }
    }

    @Override
    public BeerStyle  getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(BeerStyle .class, id);
        }
    }

    @Override
    public BeerStyle getBeerStyleByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<BeerStyle> query = session
                    .createQuery("from BeerStyle where isDeleted = false and name = :name",
                     BeerStyle .class);
            query.setParameter("name", name);
            return query.getSingleResult();
        }
    }

    public void deleteBeerStyle(int id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                BeerStyle tempStyle = getById(id);
                tempStyle.setDeleted(true);

                session.update(tempStyle);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
    }

}
