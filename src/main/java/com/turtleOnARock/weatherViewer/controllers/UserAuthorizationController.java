package com.turtleOnARock.weatherViewer.controllers;

import com.turtleOnARock.weatherViewer.DTO.AuthorizationDto;
import com.turtleOnARock.weatherViewer.exceptions.UserNotFoundException;
import com.turtleOnARock.weatherViewer.exceptions.WrongPasswordException;
import com.turtleOnARock.weatherViewer.servicies.AuthorizationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sign-in")
public class UserAuthorizationController {
    private final AuthorizationService authorizationService;

    @Autowired
    public UserAuthorizationController(AuthorizationService authorizationService){
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public String getSingIn(@ModelAttribute("authorizationDto")AuthorizationDto dto){
        return "sign-in-with-errors";
    }

    @PostMapping
    public String postSignIn(@CookieValue(value = "sessionId", defaultValue = "0") int sessionId,
                             @ModelAttribute("authorizationDto") @Valid AuthorizationDto dto,
                             BindingResult bindingResult,
                             HttpServletResponse response,
                             Model model){

        if(bindingResult.hasErrors()){
            return "sign-in-with-errors";
        }
        if(sessionId != 0){
            authorizationService.logout(sessionId);
            clearCookie("sessionId", response);
            sessionId = 0;
        }
        try{
            sessionId = authorizationService.login(dto);
            ResponseCookie cookie = createCookie("sessionId", String.valueOf(sessionId));
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }catch(UserNotFoundException | WrongPasswordException e){
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", e.getMessage());
            return "sign-in-with-errors";
        }
        return "redirect:/";
    }

    private void clearCookie(String cookieName, HttpServletResponse response){
        ResponseCookie cookie = ResponseCookie.from(cookieName, null)
                .maxAge(0)
                .path("/")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    private ResponseCookie createCookie(String cookieName, String cookieValue){
        return ResponseCookie.from(cookieName, cookieValue)
                .httpOnly(true)
                .path("/")
                .maxAge(AuthorizationService.SESSION_DURATION_MINUTES)
                .build();
    }
}
