package com.turtleOnARock.weatherViewer.DAO;

import com.turtleOnARock.weatherViewer.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Optional<User> findByLogin(String login) {
        try{
            User user = getByLogin(login);
            return Optional.of(user);
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public User getByLogin(String login) {
        User user;
        String query = "FROM User AS user WHERE user.login = :login";
        Session session = sessionFactory.getCurrentSession();
        user = session.createQuery(query, User.class).setParameter("login", login).uniqueResult();
        return user;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        String query = "FROM User";
        Session session = sessionFactory.getCurrentSession();
        users = session.createQuery(query, User.class).getResultList();
        return users;
    }
}
