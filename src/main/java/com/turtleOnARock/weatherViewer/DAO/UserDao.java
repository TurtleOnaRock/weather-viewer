package com.turtleOnARock.weatherViewer.DAO;

import com.turtleOnARock.weatherViewer.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> findByLogin(String login);

    User getByLogin (String login);

    void save(User user);

    List<User> getAll();

}
