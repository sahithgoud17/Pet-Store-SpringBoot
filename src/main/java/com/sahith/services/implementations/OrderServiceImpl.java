package com.sahith.services.implementations;

import com.sahith.dao.CustomerRepository;
import com.sahith.dao.OrderRepository;
import com.sahith.entity.Customer;
import com.sahith.entity.Order;
import com.sahith.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(int id) {
        Optional<Order> result = orderRepository.findById(id);
        Order order = null;
        if(result.isPresent()) {
            order = result.get();
        }
        else {
            throw new RuntimeException("Did not find Order Id - " + id);
        }
        return order;
    }

    @Override
    public List<Order> getCustomerOrders(Integer customerId) {
        return orderRepository.findByCustomerIdAndIsPlaced(customerId, true);
    }

    @Override
    public List<Order> getCustomerCart(Integer customerId) {
        return orderRepository.findByCustomerIdAndIsPlaced(customerId, false);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void createOrder(int customerId) {
        Optional<Customer> customerResult = customerRepository.findById(customerId);
        if(customerResult.isPresent()) {
            Customer customer = customerResult.get();
            Order order = new Order(false, customer, new ArrayList<>());
            save(order);
        }
    }

    @Override
    public void createOrder(String email) {
        Customer customer = customerRepository.findByEmail(email);
        Order order = new Order(false, customer, new ArrayList<>());
        save(order);
    }

    @Override
    public Order findCartOrder(int customerId) {
        List<Order> orders = orderRepository.findByCustomerIdAndIsPlaced(customerId, false);
        if(orders.isEmpty())
            return null;
        return orders.get(0);
    }

}