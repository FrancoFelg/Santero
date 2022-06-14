package com.app.santero.controllers;

import com.app.santero.entities.User;
import com.app.santero.repositories.UserDAO;
import com.app.santero.repositories.UserDAOImpl;
import com.app.santero.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDAOImpl userDAO;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios")
    //@RequestHeader(value="Authorization") String token
    public List<User> getAllUsers(){//Pide que el token esté presente
        //if(validateToken(token) )
        //   return null;
        return userDAO.getAllUsers();
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestHeader(value="Authorization") String token,
                         @PathVariable Long id){
        if(validateToken(token) )
            return;
        userDAO.delete(id);
    }

    private boolean validateToken(String token){
        String usuarioId = jwtUtil.getKey(token); //Obtiene la ID del usuario segun su token
        return usuarioId == null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void register(@RequestBody User user){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);//Crea un hasheador
        String hash = argon2.hash(1,1024,1,user.getPassword());//Settea la cantidad de iteraciones, xxx, la cantidad de subtareas y la contraseña a hashear
        user.setPassword(hash);//Setteamos la contraseña hasheada

        userDAO.register(user);
    }

}
