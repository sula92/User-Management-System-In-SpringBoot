package com.sula.coffeeshop.repository.custom;

import com.sula.coffeeshop.entity.OrderDetail;
import com.sula.coffeeshop.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
}
