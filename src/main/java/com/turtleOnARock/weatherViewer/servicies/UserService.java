package com.turtleOnARock.weatherViewer.servicies;

import com.turtleOnARock.weatherViewer.DAO.UserRepository;
import com.turtleOnARock.weatherViewer.DTO.UserRegistrationDto;
import com.turtleOnARock.weatherViewer.entities.User;
import com.turtleOnARock.weatherViewer.exceptions.NoteAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void save(UserRegistrationDto userInputDTO){
        if(userRepository.findByLogin(userInputDTO.getEmail()).isPresent()) {
            throw new NoteAlreadyExistException("User '" + userInputDTO.getEmail() + "' already exist!");
        }
        String hashedPassword = PasswordUtils.hashPassword(userInputDTO.getPassword());
        String login = userInputDTO.getEmail();
        User user = new User(login, hashedPassword);
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.getAll();
    }

    public User getUser(int userId) {
        return userRepository.getById(userId);
    }
}
