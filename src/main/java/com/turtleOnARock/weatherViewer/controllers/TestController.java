package com.turtleOnARock.weatherViewer.controllers;

import com.turtleOnARock.weatherViewer.DTO.TestSignInDto;
import com.turtleOnARock.weatherViewer.entities.AppSession;
import com.turtleOnARock.weatherViewer.entities.User;
import com.turtleOnARock.weatherViewer.servicies.AuthorizationService;
import com.turtleOnARock.weatherViewer.servicies.SessionService;
import com.turtleOnARock.weatherViewer.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @GetMapping
    public String getAllUsers (@CookieValue(value = "sessionId", defaultValue = "0") int sessionId,
                               Model model){
        TestSignInDto testSignInDto = new TestSignInDto();
        List<User> users = userService.getAllUsers();
        List<AppSession> sessions = sessionService.getAllSessions();

        if(sessionId != 0){
            AppSession appSession = sessionService.getSession(sessionId);
            User user = userService.getUser(appSession.getUserId());
            testSignInDto.setLogin(user.getLogin());
            testSignInDto.setExpiresAt(appSession.getExpiresAt());
            testSignInDto.setSessionId(sessionId);
        }

        model.addAttribute("appSessions", sessions);
        model.addAttribute("testSignInDto", testSignInDto);
        model.addAttribute("users", users);
        return "test";
    }

}
