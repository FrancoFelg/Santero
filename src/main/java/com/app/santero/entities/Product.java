package com.app.santero.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter @Column(name="Id")
    private Long id;

    @Getter @Setter @Column(name="Name")
    private String name;

    @Getter @Setter @Column(name="Description")
    private String description;

    @Getter @Setter @Column(name="Image")
    private String image;

    @Getter @Setter @Column(name="Price")
    private String price;

}
