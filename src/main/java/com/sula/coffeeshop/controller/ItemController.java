package com.sula.coffeeshop.controller;

import com.sula.coffeeshop.dto.ItemDTO;
import com.sula.coffeeshop.service.custom.ItemService;
import com.sula.coffeeshop.service.custom.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemServiceImpl itemService;

    @GetMapping
    public List<ItemDTO> getAllItems() throws Exception {
        return itemService.getAllItems();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void saveItem(@ModelAttribute @Valid ItemDTO coffee) throws Exception {
        if (itemService.itemExist(coffee.getCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        itemService.saveItem(coffee.getCode(),coffee.getDescription(),coffee.getQtyOnHand(),coffee.getUnitPrice().doubleValue());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{code}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void updateItem(@PathVariable @Valid @Pattern(regexp = "I\\{3}") String code,@RequestBody MultiValueMap<String,String> params) throws Exception {
        if (!itemService.itemExist(code) || params.get("description").size()==0 || params.get("unitPrice").size()==0 || params.get("qtyOnHand").size()==0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        itemService.updateItem(params.get("description").get(0),Integer.parseInt(params.get("qtyOnHand").get(0)),Double.parseDouble(params.get("unitPrice").get(0)),code);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void deleteItem(@PathVariable @Valid @Pattern(regexp = "I\\{3}") String code) throws Exception {
        if (!itemService.itemExist(code)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        itemService.deleteItem(code);
    }

}
