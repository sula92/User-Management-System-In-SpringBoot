package com.sula.coffeeshop.repository.custom;

import com.sula.coffeeshop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,String> {

    Item getFirstLastItemCodeByOrderByCodeDesc() throws Exception;

}
