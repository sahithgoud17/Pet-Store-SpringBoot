package com.sahith.services.interfaces;

import com.sahith.entity.Order;
import java.util.List;

public interface OrderService {

    public List<Order> findAll();

    public Order findOrderById(int id);

    public List<Order> getCustomerOrders(Integer customerId);

    public List<Order> getCustomerCart(Integer customerId);

    public void save(Order order);

    public void createOrder(int customerId);

    public void createOrder(String email);

    public Order findCartOrder(int customerId);
}
