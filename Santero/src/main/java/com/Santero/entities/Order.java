package com.Santero.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
/**
 * @author Lamberti
 */
public class Order {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(nullable = false)
	private Long number;
	
	@OneToOne
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	private Payment payment;

	public Order() {}
	
	public Order(String id, Long number, Cart cart, User user, Payment payment) {
		super();
		this.id = id;
		this.number = number;
		this.cart = cart;
		this.user = user;
		this.payment = payment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
}
