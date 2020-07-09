package com.telebeer.beertag.repositories;

import com.telebeer.beertag.models.Entities.Beer;
import com.telebeer.beertag.models.Entities.Rating;
import com.telebeer.beertag.models.Entities.User;
import com.telebeer.beertag.repositories.contracts.RatingRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RatingRepositoryImpl implements RatingRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public RatingRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void rateBeer(User user, Beer beer, Rating rating) {
        try (Session currentSession = sessionFactory.openSession()) {
            try {
                currentSession.beginTransaction();

                currentSession.saveOrUpdate(rating);
                currentSession.saveOrUpdate(beer);
                currentSession.saveOrUpdate(user);

                currentSession.getTransaction().commit();
            } catch (HibernateException e) {
                currentSession.getTransaction().rollback();
            }
        }
    }

}
