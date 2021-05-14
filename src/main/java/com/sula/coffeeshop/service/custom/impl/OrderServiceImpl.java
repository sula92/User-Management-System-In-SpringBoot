package com.sula.coffeeshop.service.custom.impl;

import com.sula.coffeeshop.dto.ItemDTO;
import com.sula.coffeeshop.dto.OrderDTO;
import com.sula.coffeeshop.dto.OrderDetailDTO;
import com.sula.coffeeshop.entity.Customer;
import com.sula.coffeeshop.entity.Item;
import com.sula.coffeeshop.entity.Order;
import com.sula.coffeeshop.entity.OrderDetail;
import com.sula.coffeeshop.repository.custom.CustomerRepository;
import com.sula.coffeeshop.repository.custom.OrderDetailRepository;
import com.sula.coffeeshop.repository.custom.OrderRepository;
import com.sula.coffeeshop.service.custom.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public String getNewOrderId() throws Exception {
        String lastItemCode = orderRepository.getFirstLastOrderIdByOrderByIdDesc().getId();
        if (lastItemCode == null) {
            return "OD001";
        } else {
            int maxId = Integer.parseInt(lastItemCode.replace("OD", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "OD00" + maxId;
            } else if (maxId < 100) {
                id = "OD0" + maxId;
            } else {
                id = "OD" + maxId;
            }

            return id;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> searchOrder(String oid) throws Exception {
        List<OrderDTO> orderDTOS=new ArrayList<>();
        List<Order> orders=orderRepository.searchOrder(oid);
        orders.stream().forEach(order -> {
            orderDTOS.add(new OrderDTO(order.getId(),order.getDate().toString(),order.getCustomer().getId()));
        });
        return orderDTOS;
    }

    @Override
    public void placeOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetails) throws Exception {

        List<OrderDetail> orderDetailList=new ArrayList<>();
        orderDetails.stream().forEach(odto -> {
            orderDetailList.add(new OrderDetail(odto.getCode(),orderDTO.getId(),odto.getQty(),odto.getUnitPrice()));
        });

        Date date= Date.valueOf(orderDTO.getDate());

        Order order=new Order(orderDTO.getId(),date,customerRepository.getOne(orderDTO.getCustomerId()));
        orderRepository.save(order);

    }

    @Override
    public OrderDTO getOrder(String id) {

       Order order=orderRepository.getOne(id);
       OrderDTO orderDTO=new OrderDTO(order.getId(),order.getDate().toString(),order.getCustomer().getId());
       return orderDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrders() {

        List<Order> allOrders = orderRepository.findAll();
        List<OrderDTO> orders = new ArrayList<>();
        for (Order order : allOrders) {
            List<OrderDetailDTO> orderDetailDTOS=new ArrayList<>();
            order.getOrderDetailList().stream().forEach(orderDetail -> {
                orderDetailDTOS.add(new OrderDetailDTO(orderDetail.getItem().getCode(), orderDetail.getQty(), orderDetail.getUnitPrice() ));
            });
            orders.add(new OrderDTO(order.getId(), order.getDate().toString(), order.getCustomer().getId(),orderDetailDTOS));
        }
        return orders;
    }

    @Override
    public boolean orderExist(String oid) {
        return orderRepository.existsById(oid);
    }

    @Override
    public void deleteOrder(String oid) {
        orderRepository.deleteById(oid);
    }

    @Override
    public void updateOrder(String id, Date date, Customer customer) {
        orderRepository.save(new Order());
    }
}
