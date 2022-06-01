package com.sahith.serviceTest;

import com.sahith.dao.OrderRepository;
import com.sahith.entity.Category;
import com.sahith.entity.Customer;
import com.sahith.entity.Item;
import com.sahith.entity.Order;
import com.sahith.services.implementations.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderServiceImplTest
{
    @InjectMocks
    OrderServiceImpl orderService;
    @Mock
    OrderRepository orderRepository;

    @Test
    void returnTheOrdersIfExists()
    {
        Customer customer = new Customer(6 , "Sahith" , "Goud" , "sahithgoud.d17@gmail.com" ,"abc");
        Category category = new Category(1 , "Dog");
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(22 , "lucy" , "pug" , 1 , 5 , "brown" , 1 ,  25000 , "xyz" , category ));
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(10 , true , customer , itemList));
        when(orderRepository.findAll()).thenReturn(orders);
        assertEquals(orders, orderService.findAll());
    }

    @Test
    void returnNullIfOrdersDoesNotExist()
    {
        List<Order> order = null;
        when(orderRepository.findAll()).thenReturn(order);
        assertEquals(order,orderService.findAll());
    }

    @Test
    void returnOrderByIdIfExists()
    {
        Customer customer = new Customer(6 , "Sahith" , "Goud" , "sahithgoud.d17@gmail.com" ,"abc");
        Category category = new Category(1 , "Dog");
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(22 , "lucy" , "pug" , 1 , 5 , "brown" , 1 ,  25000 , "xyz" , category ));
        Order order = new Order(1 , true , customer , itemList);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        assertEquals(order , orderService.findOrderById(1));
    }

    @Test
    void returnNullIfOrderByIdDoesNotExists() throws RuntimeException
    {
        Optional<Order> order = Optional.empty();
        when(orderRepository.findById(2)).thenReturn(order);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            orderService.findOrderById(2);
        });
        assertEquals("Did not find Order Id - " + 2, thrown.getMessage());
    }

    @Test
    void saveOrder()
    {
        Customer customer = new Customer(6 , "Sahith" , "Goud" , "sahithgoud.d17@gmail.com" ,"abc");
        Category category = new Category(1 , "Dog");
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(22 , "lucy" , "pug" , 1 , 5 , "brown" , 1 ,  25000 , "xyz" , category ));
        Order order = new Order(2 , true , customer , itemList);
        orderService.save(order);
        verify(orderRepository,times(1)).save(order);
    }

    @Test
    void returnCustomerOrdersTest() {
        List<Item> items = new ArrayList<>();
        Category category = new Category(1, "Dog");
        items.add(new Item(22 , "lucy" , "pug" , 1 , 5 , "brown" , 1 ,  25000 , "xyz" , category ));
        Customer customer = new Customer(1,"Elon","Musk","elon@gmail.com","Elon@123");
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1,true, customer, items);
        orders.add(order);
        when(orderRepository.findByCustomerIdAndIsPlaced(1,true)).thenReturn(orders);
        assertEquals(orders, orderService.getCustomerOrders(1));
    }

    @Test
    void ShouldReturnCustomerCartTest() {
        List<Item> items = new ArrayList<>();
        Category category = new Category(1, "Dog");
        Customer customer = new Customer(1,"Sahith","Goud","sahithgoud.d17@gmail.com","abc");
        items.add(new Item(22 , "lucy" , "pug" , 1 , 5 , "brown" , 1 ,  25000 , "xyz" , category ));
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1,false, customer, items);
        orders.add(order);
        when(orderRepository.findByCustomerIdAndIsPlaced(1,false)).thenReturn(orders);
        assertEquals(orders, orderService.getCustomerCart(1));
    }

    @Test
    void ShouldFindCartOrderByIdTest() {
        List<Item> items = new ArrayList<>();
        Category category = new Category(1, "Dog");
        Customer customer = new Customer(1,"Sahith","Goud","sahithgoud.d17@gmail.com","abc");
        items.add(new Item(22 , "lucy" , "pug" , 1 , 5 , "brown" , 1 ,  25000 , "xyz" , category ));
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1,false, customer, items);
        orders.add(order);
        when(orderRepository.findByCustomerIdAndIsPlaced(1, false)).thenReturn(orders);
        assertEquals(order, orderService.findCartOrder(1));
    }

}
