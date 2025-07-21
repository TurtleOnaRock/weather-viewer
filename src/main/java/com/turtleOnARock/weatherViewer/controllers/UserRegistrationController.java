package com.turtleOnARock.weatherViewer.controllers;

import com.turtleOnARock.weatherViewer.DTO.UserRegistrationDto;
import com.turtleOnARock.weatherViewer.exceptions.NoteAlreadyExistException;
import com.turtleOnARock.weatherViewer.servicies.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sign-up")
public class UserRegistrationController {

    @GetMapping()
    public String getSignUp(Model model){
        model.addAttribute("userRegistrationDTO", new UserRegistrationDto());
        return "sign-up-with-errors";
    }

    @PostMapping()
    public String postSignUp(@ModelAttribute("userRegistrationDTO") @Valid UserRegistrationDto dto,
                             BindingResult bindingResult,
                             Model model){
        if(bindingResult.hasErrors()){
            return "sign-up-with-errors";
        }
        if(different(dto.getPassword(), dto.getRepeatedPassword())){
            model.addAttribute("error", true);
            model.addAttribute("unmatchedPasswords", true);
            return "sign-up-with-errors";
        }
        try {
            UserService service = new UserService();
            service.save(dto);
        }catch (NoteAlreadyExistException e){
            model.addAttribute("error", true);
            model.addAttribute("userAlreadyExist", true);
            return "sign-up-with-errors";
        }
        return "redirect:/test";
    }

    private boolean different(String firstString, String secondString){
        return !firstString.equals(secondString);
    }
}
