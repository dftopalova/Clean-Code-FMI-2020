package com.telebeer.beertag.repositories;

import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.repositories.contracts.UserRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAll() {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<User> query = currentSession.createQuery("from User where enabled = true ", User.class);
            return query.list();
        }
    }

    @Override
    public User getById(int id) {
        try (Session currentSession = sessionFactory.openSession()) {
            return currentSession.get(User.class, id);
        }
    }

    @Override
    public User getByUsername(String username) {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<User> query = currentSession.createQuery(
                    "from User where username = :username " +
                            "and enabled = true ", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        }
    }

    @Override
    public List<User> getAllByFirstName(String firstName) {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<User> query = currentSession.createQuery(
                    "from User where firstName = :firstName " +
                            "and enabled = true ", User.class);
            query.setParameter("firstName", firstName);
            return query.list();
        }
    }

    @Override
    public List<User> getAllByLastName(String lastName) {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<User> query = currentSession.createQuery(
                    "from User where lastName = :lastName " +
                            "and enabled = true ", User.class);
            query.setParameter("lastName", lastName);
            return query.list();
        }
    }

    @Override
    public List<User> getAllByFullName(String firstName, String lastName) {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<User> query = currentSession.createQuery(
                    "from User where lastName = :lastName " +
                            "and firstName = :firstName " +
                            "and enabled = true ", User.class);
            query.setParameter("lastName", lastName);
            query.setParameter("firstName", firstName);
            return query.list();
        }
    }


    @Override
    public void deleteUser(User user) {
        try (Session currentSession = sessionFactory.openSession()) {
            try {
                currentSession.beginTransaction();
                user.setEnabled(false);
                currentSession.update(user);
                currentSession.getTransaction().commit();
            } catch (HibernateException e) {
                currentSession.getTransaction().rollback();
            }
        }
    }

    @Override
    public void hardDeleteUser(User user) {
        try (Session currentSession = sessionFactory.openSession()) {
            try {
                currentSession.beginTransaction();
                currentSession.delete(user);
                currentSession.getTransaction().commit();
            } catch (HibernateException e) {
                currentSession.getTransaction().rollback();
            }
        }
    }

    @Override
    public void createUser(User user) {
        try (Session currentSession = sessionFactory.openSession()) {
            currentSession.saveOrUpdate(user);
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session currentSession = sessionFactory.openSession()) {
            try {
                currentSession.beginTransaction();
                currentSession.update(user);
                currentSession.getTransaction().commit();
            } catch (HibernateException e) {
                currentSession.getTransaction().rollback();
            }
        }
    }

    @Override
    public void markBeerAsTested(User user, Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                user.getTestedBeers().add(beer);

                session.update(user);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void markBeerAsWish(User user, Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                user.getBeersWishlist().add(beer);

                session.update(user);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeBeerFromTestedList(User user, Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                user.getTestedBeers().remove(beer);

                session.update(user);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeBeerFromWishes(User user, Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                user.getBeersWishlist().remove(beer);

                session.update(user);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
    }

}
