package com.sula.coffeeshop.repository.custom;

import com.sula.coffeeshop.dto.OrderDTO;
import com.sula.coffeeshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.management.loading.MLetContent;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
    public Order getFirstLastOrderIdByOrderByIdDesc();

    @Query("select O from Order O where O.id LIKE '%:oid%'")
    public List<Order> searchOrder(@Param("oid") String oid);

   // public boolean orderExist(String oid);
}
