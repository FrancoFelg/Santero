package com.app.santero.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ClientOrders")
public class ClientOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter @Column(name="Id")
    private Long id;

    @Getter @Setter @Column(name="Products")
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> products;

    @Getter @Setter @Column(name="Total")
    private Double total;


}
