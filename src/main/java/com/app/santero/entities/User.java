package com.app.santero.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter @Column(name="Id")
    private Long id;

    @Getter @Setter @Column(name="Name")
    private String name;

    @Getter @Setter @Column(name="Lastname")
    private String lastname;

    @Getter @Setter @Column(name="Email")
    private String email;

    @Getter @Setter @Column(name="Password")
    private String password;

}
