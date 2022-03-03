package com.Santero.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Santero.entities.Cart;
import com.Santero.entities.Order;
import com.Santero.entities.User;
import com.Santero.repositories.OrderRepository;

@Service
/**
 * @author Lamberti
 */
public class OrderService {

	@Autowired
	private Validator validator;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//Create
	/**
	 * @author Franco Lamberti
	 * Este método guarda y retorna una orden como objeto. Se puede usar para crear o editar.
	 * @param order - Es la orden a guardar
	 */
	public Order save(Order order) throws Exception {
		validator.notNullObject(order, "Order");
		return orderRepository.save(order);
	}
	
	/**
	 * @author Franco Lamberti
	 * Este método obtiene todos los atributos de una orden y crea un objeto usando los mismos. Se puede usar para crear o editar.
	 * @param number
	 * @param cart
	 * @param user
	 * @param payment
	 */
	public Order save(Long number, Cart cart, User user, Payment payment) throws Exception {
		List<Object> objects = Arrays.asList(number, cart, user, payment);
		List<String> names = Arrays.asList("Number", "Cart", "User", "Payment");
		validator.notNullObjects(objects, names);
		
		Order order = new Order();
		order.setCart(cart);
		order.setNumber(number);
		order.setPayment(payment);
		order.setUser(user);
		
		orderRepository.save(order);
	}
	
	//Delete
	public void delete(Order order) throws Exception {
		validator.notNullObject(order, "Order");
		orderRepository.delete(order);
	}
	
	//Getters
	public List<Order> getAll(){
		return orderRepository.findAll();
	}
	
	public Order getById(String id) throws Exception{
		validator.notNullObject(id, "Id");
		return orderRepository.getById(id);
	}
}
