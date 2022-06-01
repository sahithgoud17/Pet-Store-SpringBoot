package com.sahith.services.implementations;

import com.sahith.dao.CustomerRepository;
import com.sahith.entity.Customer;
import com.sahith.services.interfaces.CustomerService;
import com.sahith.utilities.CustomerSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(CustomerSignUp customer) {
        Customer customerEntity = new Customer(customer);
        save(customerEntity);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(encodePassword(customer));
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findCustomerById(int id) {
        Optional<Customer> result = customerRepository.findById(id);
        Customer customer = null;
        if(result.isPresent()) {
            customer = result.get();
        }
        else {
            throw new RuntimeException("Did not find Customer Id - " + id);
        }
        return customer;
    }

    @Override
    public Customer encodePassword(Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customer;
    }

    public boolean isNewEmail(String email) {
        List<Customer> customerList = findAll();
        if(customerList == null)
            return true;
        for (Customer customer : customerList) {
            if(customer.getEmail().equals(email))
                return false;
        }
        return true;
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public String getCurrentUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @Override
    public int getCustomerIdByEmail(String email) {
        return findCustomerByEmail(email).getId();

    }


}