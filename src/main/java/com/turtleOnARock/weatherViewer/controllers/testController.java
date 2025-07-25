package com.turtleOnARock.weatherViewer.controllers;

import com.turtleOnARock.weatherViewer.DAO.UserRepository;
import com.turtleOnARock.weatherViewer.DAO.UserRepositoryImpl;
import com.turtleOnARock.weatherViewer.entities.User;
import com.turtleOnARock.weatherViewer.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class testController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers (Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "test";
    }

}
