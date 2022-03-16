package main.dao;

import main.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }
}
