package com.sula.coffeeshop.controller;

import com.sula.coffeeshop.dto.ItemDTO;
import com.sula.coffeeshop.dto.OrderDTO;
import com.sula.coffeeshop.dto.OrderDetailDTO;
import com.sula.coffeeshop.entity.Customer;
import com.sula.coffeeshop.entity.OrderDetail;
import com.sula.coffeeshop.service.custom.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(value="/searchOrders/{oid}")
    public List<OrderDTO> searchOrder(@PathVariable String oid) throws Exception {
        return orderService.searchOrder(oid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void placeOrder(@ModelAttribute @Valid OrderDTO orderDTO, @ModelAttribute @Valid List<OrderDetailDTO> orderDetailDTOS ) throws Exception {

        orderService.placeOrder(orderDTO,orderDetailDTOS);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{oid}")
    public void deleteItem(@PathVariable @Valid @Pattern(regexp = "OD\\{3}") String oid) throws Exception {
        if (!orderService.orderExist(oid)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        orderService.deleteOrder(oid);
    }



}
