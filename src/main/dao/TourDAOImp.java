package main.dao;

import main.model.Tour;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TourDAOImp implements  TourDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Tour> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Tour t", Tour.class).list();
    }

    @Override
    public Tour getbyId(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Tour.class, id);
    }

    @Override
    public void saveOrUpate(Tour tour) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tour);

    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Tour tour = getbyId(id);
        session.delete(tour);
    }

    @Override
    public Tour getByIdWithComments(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Tour t left join fetch t.comments where t.id = :id", Tour.class).setParameter("id", id).getSingleResult();
    }
}
