package com.telebeer.beertag.repositories;

import com.telebeer.beertag.models.Entities.Country;
import com.telebeer.beertag.repositories.contracts.CountryRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class CountryRepositoryImpl implements CountryRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CountryRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Map<Integer, Country> getAllCountries() {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<Country> query = currentSession
                    .createQuery("from Country where isDeleted = false", Country.class);
            return queryToMap(query);
        }
    }


    @Override
    public Country getById(int id) {
        try (Session currentSession = sessionFactory.openSession()) {
            return currentSession.get(Country.class, id);
        }
    }

    @Override
    public Country getCountryByName(String name) {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<Country> query = currentSession
                    .createQuery("from Country where isDeleted = false and name = :name",
                            Country.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        }
    }

    @Override
    public Country getCountryByCode(String code) {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<Country> query = currentSession
                    .createQuery("from Country where isDeleted = false and code = :code",
                            Country.class);
            query.setParameter("code", code);
            return query.getSingleResult();
        }
    }

    @Override
    public Map<Integer, Country> getCountriesByContinent(String continentCode) {
        try (Session currentSession = sessionFactory.openSession()) {
            Query<Country> query = currentSession
                    .createQuery("from Country where isDeleted = false and continent_code = :continent_code",
                            Country.class);
            query.setParameter("continent_code", continentCode);
            return queryToMap(query);
        }
    }

    private Map<Integer, Country> queryToMap(Query<Country> query) {
        Map<Integer, Country> result = new HashMap<>();
        List<Country> countriesList = query.list();

        for (int i = 0; i < countriesList.size(); i++) {
            result.put(countriesList.get(i).getCountry_id(), countriesList.get(i));
        }
        return result;
    }
}
