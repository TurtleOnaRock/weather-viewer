package com.turtleOnARock.weatherViewer.servicies;

import com.turtleOnARock.weatherViewer.DAO.UserDaoImpl;
import com.turtleOnARock.weatherViewer.DTO.UserRegistrationDto;
import com.turtleOnARock.weatherViewer.entities.User;
import com.turtleOnARock.weatherViewer.exceptions.NoteAlreadyExistException;

public class UserService {


    public void save(UserRegistrationDto userInputDTO){
        UserDaoImpl dao = new UserDaoImpl();
        if(dao.findByLogin(userInputDTO.getEmail()).isPresent()) {
            throw new NoteAlreadyExistException("User '" + userInputDTO.getEmail() + "' already exist!");
        }
        String hashedPassword = PasswordUtils.hashPassword(userInputDTO.getPassword());
        String login = userInputDTO.getEmail();
        User user = new User(login, hashedPassword);
        dao.save(user);
    }
}
