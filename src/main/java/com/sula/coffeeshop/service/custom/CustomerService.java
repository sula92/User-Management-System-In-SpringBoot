package com.sula.coffeeshop.service.custom;

import com.sula.coffeeshop.dto.CustomerDTO;
import com.sula.coffeeshop.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {

    public String getNewCustomerId() throws Exception;
    public List<CustomerDTO> getAllCustomers() throws Exception;
    public void saveCustomer(String id, String name, String address) throws Exception;
    public void deleteCustomer(String customerId) throws Exception;
    public void updateCustomer(String name, String address, String customerId) throws Exception;
    CustomerDTO getCustomer(String id) throws Exception;
    boolean customerExist(String id) ;

}
