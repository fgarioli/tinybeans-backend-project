package com.tinybeans.backend.evaluation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinybeans.backend.evaluation.data.entity.Item;
import com.tinybeans.backend.evaluation.data.repository.ItemRepository;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("")
    @ResponseBody
    public List<Item> getItems() {
        return itemRepository.findAll();
    }
}
