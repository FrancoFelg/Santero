package com.app.santero.repositories;

import com.app.santero.entities.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        String query = "FROM User";
        System.out.println(entityManager.createQuery(query).getResultList().toString());
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void register(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getByCredentials(User user) {
        String query = "FROM User WHERE email = :email"; //Obtengo el usuario
        List<User> list = entityManager.createQuery(query) //Creo una query
                .setParameter("email", user.getEmail()) //Setteo un parametro, que es el email
                .getResultList(); //Obtengo el resultado

        if(list.isEmpty()){
            return null;
        }

        String passwordHashed = list.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id); //Creo un objetoer que me permita verificar la password hash

        if(argon2.verify(passwordHashed, user.getPassword())){//Verifico la password hasheada enviada con la guardada)
            return list.get(0);
        }
        return null;
    }
}
