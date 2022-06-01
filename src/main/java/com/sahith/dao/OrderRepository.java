package com.sahith.dao;


import com.sahith.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findByCustomerIdAndIsPlaced(int customerId, boolean isPlaced);

}
