package com.sula.coffeeshop.service.custom.impl;

import com.sula.coffeeshop.dto.CustomerDTO;
import com.sula.coffeeshop.entity.Customer;
import com.sula.coffeeshop.repository.custom.CustomerRepository;
import com.sula.coffeeshop.service.custom.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public String getNewCustomerId() throws Exception {
        String lastCustomerId = customerRepository.getFirstLastCustomerIdByOrderByIdDesc().getId();
        if (lastCustomerId == null) {
            return "C001";
        } else {
            int maxId = Integer.parseInt(lastCustomerId.replace("C", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "C00" + maxId;
            } else if (maxId < 100) {
                id = "C0" + maxId;
            } else {
                id = "C" + maxId;
            }
            return id;
        }
    }

    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers() throws Exception {
        List<Customer> allCustomers = null;

        allCustomers = customerRepository.findAll();

        List<CustomerDTO> customerTMS = new ArrayList<>();
        for (Customer customer : allCustomers) {
            customerTMS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress()));
        }
        return customerTMS;
    }

    public void saveCustomer(String id, String name, String address) throws Exception {
        customerRepository.save(new Customer(id, name, address));
    }

    public void deleteCustomer(String customerId) throws Exception {
        customerRepository.deleteById(customerId);
    }

    public void updateCustomer(String name, String address, String customerId) throws Exception {
        customerRepository.save(new Customer(customerId, name, address));

    }

    @Override
    public CustomerDTO getCustomer(String id) throws Exception{
        Customer customer = customerRepository.findById(id).get();
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
    }

    @Override
    public boolean customerExist(String id){
        try {
            customerRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
