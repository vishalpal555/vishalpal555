package com.vishal.skillvertexProject.FoodItems;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/items")
public class ItemController {
    
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello";        
    }

}
