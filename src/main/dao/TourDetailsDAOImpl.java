package main.dao;

import main.model.Tour;
import main.model.TourDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TourDetailsDAOImpl implements TourDetailsDAO{

    // you need this to implement hibernate
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TourDetails getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TourDetails.class, id);
    }

    @Override
    public void saveOrUpdate(TourDetails tourDetails) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tourDetails);

    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        TourDetails tourDetails = getById(id);
        session.delete(tourDetails);

    }
}
