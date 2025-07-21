package com.turtleOnARock.weatherViewer.DAO;

import com.turtleOnARock.weatherViewer.exceptions.DataBaseException;
import com.turtleOnARock.weatherViewer.entities.User;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

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
        try(Session session = DataSource.getSessionFactory().openSession()){
            session.beginTransaction();
            user = session.createQuery(query, User.class).setParameter("login", login).uniqueResult();
            session.getTransaction().commit();
        }catch(Exception e){
            throw new DataBaseException("Problem with getting by login");
        }
        return user;
    }

    @Override
    public void save(User user) {
        try(Session session = DataSource.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception e){
            throw new DataBaseException("Problem with saving user");
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        String query = "FROM User";
        try(Session session = DataSource.getSessionFactory().openSession()){
            session.beginTransaction();
            users = session.createQuery(query, User.class).getResultList();
            session.getTransaction().commit();
        } catch(Exception e){
            throw new DataBaseException(e.getMessage());
        }
        return users;
    }
}
