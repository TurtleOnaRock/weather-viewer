package com.turtleOnARock.weatherViewer.controllers;

import com.turtleOnARock.weatherViewer.DAO.UserDaoImpl;
import com.turtleOnARock.weatherViewer.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class testController {

    @GetMapping
    public String getAllUsers (Model model){
        UserDaoImpl dao = new UserDaoImpl();
        List<User> users = dao.getAll();
        model.addAttribute("users", users);
        return "test";
    }

}
