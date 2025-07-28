package com.turtleOnARock.weatherViewer.DAO;

import com.turtleOnARock.weatherViewer.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByLogin(String login);

    User getByLogin (String login);

    User getById(int userId);

    void save(User user);

    List<User> getAll();

}
