package com.app.santero.controllers;

import com.app.santero.entities.User;
import com.app.santero.repositories.UserDAO;

import com.app.santero.repositories.UserDAOImpl;
import com.app.santero.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDAOImpl userDAO;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "auth/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){

        User loggedUser = userDAO.getByCredentials(user);
        if( loggedUser != null ){

            String token = jwtUtil.create(String.valueOf(loggedUser.getId()), loggedUser.getEmail());
            System.out.println("The user exists="+token);
            return token;
        }else{
            System.out.println("The user doesn't exists");
            return "Authentication Failed";
        }
    }

}
