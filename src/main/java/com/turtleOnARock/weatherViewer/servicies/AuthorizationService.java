package com.turtleOnARock.weatherViewer.servicies;

import com.turtleOnARock.weatherViewer.DAO.SessionRepository;
import com.turtleOnARock.weatherViewer.DAO.UserRepository;
import com.turtleOnARock.weatherViewer.DTO.AuthorizationDto;
import com.turtleOnARock.weatherViewer.entities.AppSession;
import com.turtleOnARock.weatherViewer.entities.User;
import com.turtleOnARock.weatherViewer.exceptions.UserNotFoundException;
import com.turtleOnARock.weatherViewer.exceptions.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class AuthorizationService {
    public static final int SESSION_DURATION_MINUTES = 24;

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public AuthorizationService(UserRepository userRepository, SessionRepository sessionRepository){
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public int login(AuthorizationDto dto) {
        String inputUsername = dto.getLogin();
        Optional<User> userOptional = userRepository.findByLogin(inputUsername);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(PasswordUtils.checkPassword(user.getPassword(), dto.getPassword())){
                int sessionId = createSession(user);
                return sessionId;
            }
            throw new WrongPasswordException("Password is wrong!");
        }
        throw new UserNotFoundException("User '" + inputUsername +"' doesn't exist.");
    }

    public void logout(int sessionId) {
        AppSession session = sessionRepository.getById(sessionId);
        sessionRepository.delete(session);
    }

    private int createSession(User user){
        int userId = user.getId();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(SESSION_DURATION_MINUTES);
        AppSession session = new AppSession(userId, expiresAt);
        sessionRepository.save(session);
        return session.getId();
    }

}
