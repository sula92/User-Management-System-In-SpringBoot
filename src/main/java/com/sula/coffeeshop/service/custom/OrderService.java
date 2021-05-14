package com.sula.coffeeshop.service.custom;

import com.sula.coffeeshop.dto.OrderDTO;
import com.sula.coffeeshop.dto.OrderDetailDTO;
import com.sula.coffeeshop.entity.Customer;

import java.sql.Date;
import java.util.List;

public interface OrderService {

    public String getNewOrderId() throws Exception;
    public List<OrderDTO> searchOrder(String oid) throws Exception;
    public void placeOrder(OrderDTO order, List<OrderDetailDTO> orderDetails) throws Exception;
    OrderDTO getOrder(String id);
    List<OrderDTO> getAllOrders();
    boolean orderExist(String oids);
    public void deleteOrder(String oid);
    public void updateOrder(String id, Date date, Customer customer);

}
