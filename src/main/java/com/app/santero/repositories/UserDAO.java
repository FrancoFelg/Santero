package com.app.santero.repositories;

import com.app.santero.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers();

    void delete(Long id);

    void register(User User);

    User getByCredentials(User User);

}
