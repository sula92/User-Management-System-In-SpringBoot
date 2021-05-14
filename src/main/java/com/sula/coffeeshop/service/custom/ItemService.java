package com.sula.coffeeshop.service.custom;
import com.sula.coffeeshop.dto.ItemDTO;
import com.sula.coffeeshop.service.SuperService;

import java.util.List;

public interface ItemService extends SuperService {

    public String getNewItemCode() throws Exception;
    public List<ItemDTO> getAllItems() throws Exception;
    public void saveItem(String code, String description, int qtyOnHand, double unitPrice) throws Exception;
    public void deleteItem(String itemCode) throws Exception;
    public void updateItem(String description, int qtyOnHand, double unitPrice, String itemCode) throws Exception;
    ItemDTO getItem(String code);
    boolean itemExist(String code);

}
