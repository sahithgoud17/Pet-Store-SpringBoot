package com.sahith.serviceTest;


import com.sahith.dao.CustomerRepository;
import com.sahith.entity.Customer;
import com.sahith.entity.Item;
import com.sahith.services.implementations.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerServiceImplTest
{
    @InjectMocks
    CustomerServiceImpl customerService;
    @Mock
    CustomerRepository customerRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void finAllTest()
    {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer(1 , "Sahtih" , "Goud" , "sahithgoud.d17@gmail.com" , "abc"));
        when(customerRepository.findAll()).thenReturn(customerList);
        assertEquals(customerList , customerService.findAll());
    }

    @Test
    void returnNullifCustomersDoesNotExist()
    {
        List<Customer> customers = null;
        when(customerRepository.findAll()).thenReturn(customers);
        assertEquals(customers , customerService.findAll());
    }

    @Test
    void returnCustomerById()
    {
        Customer customer = new Customer(1 , "Sahith" , "Goud" , "sahihtgoud.d17@gmail.com" , "abc");
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        assertEquals(customer , customerService.findCustomerById(1));
    }

    @Test
    void throwExceptionIfCustomerDoesNotExists() throws RuntimeException
    {
        Optional<Customer> customer = Optional.empty();
        when(customerRepository.findById(2)).thenReturn(customer);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            customerService.findCustomerById(2);
        });
        assertEquals("Did not find Customer Id - " + 2, thrown.getMessage());
    }


    @Test
    void ShouldEncodePasswordTest() {
        Customer customer = new Customer(1 , "Sahith" , "Goud" , "sahihtgoud.d17@gmail.com" , "abc");
        when(passwordEncoder.encode(customer.getPassword())).thenReturn(customer.getPassword());
        assertEquals(customer, customerService.encodePassword(customer));
    }

    @Test
    void saveCustomerTest()
    {
        Customer customer = new Customer(1 , "Sahith" , "Goud" , "sahihtgoud.d17@gmail.com" , "abc");
        customerService.save(customer);
        verify(customerRepository ,times(1)).save(customer);
    }

    @Test
    void deleteCustomerById()
    {
        Customer customer = new Customer(5 , "Sahith" , "Goud" , "sahihtgoud.d17@gmail.com" , "abc");
        customerService.save(customer);
        customerService.deleteCustomer(5);
        verify(customerRepository , times(1)).deleteById(5);
    }

    @Test
    void returnCustomerByEmail()
    {
        Customer customer = new Customer(5 , "Sahith" , "Goud" , "sahihtgoud.d17@gmail.com" , "abc");
        when(customerRepository.findByEmail("sahithgoud.d17@gmail.com")).thenReturn(customer);
        assertEquals(customer , customerService.findCustomerByEmail("sahithgoud.d17@gmail.com"));
    }

    @Test
    void returnCustomerIdByEmail()
    {
        Customer customer = new Customer(5 , "Sahith" , "Goud" , "sahihtgoud.d17@gmail.com" , "abc");
        when(customerRepository.findByEmail("sahithgoud.d17@gmail.com")).thenReturn(customer);
        assertEquals(customer.getId() , customerService.getCustomerIdByEmail("sahithgoud.d17@gmail.com"));
    }

}
