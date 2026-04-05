package com.junauto.practicespringlogin.controllers;

import com.junauto.practicespringlogin.models.OrderModel;
import com.junauto.practicespringlogin.services.OrderBusinessService;

import com.junauto.practicespringlogin.services.OrdersBusinessServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    // dependency injection.

    //OrderBusinessService service;
    OrdersBusinessServiceInterface service;

    @Autowired
    public OrdersController(OrdersBusinessServiceInterface service) {
        super();
        this.service = service;
    }

    @GetMapping("/")
    public String showAllOrders(Model model){
        //generate some orders
        //this version of the app will send a hard-coded list of orders


        List<OrderModel> orders = service.getOrders();


        model.addAttribute("title","Here is what I want to do this summer");
        model.addAttribute("orders", orders);
        return  "orders";
    }


    @GetMapping("/test")
    public String test(){
        return "orders";
    }
}
