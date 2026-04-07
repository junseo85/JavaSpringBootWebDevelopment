package com.junauto.practicespringlogin.controllers;

import com.junauto.practicespringlogin.models.OrderModel;
import com.junauto.practicespringlogin.services.OrdersBusinessServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")// api version 1 -> later can new one to api/v2   (makes it scalable)
public class OrdersRestController {

    // dependency injection.

    //OrderBusinessService service;
    OrdersBusinessServiceInterface service;

    @Autowired
    public OrdersRestController(OrdersBusinessServiceInterface service) {
        super();
        this.service = service;
    }

    @GetMapping("/")
    public List<OrderModel> showAllOrders(){
        //getAllOrders
        //generate some orders
        //this version of the app will send a hard-coded list of orders


        List<OrderModel> orders = service.getOrders();

        return orders;
    }
    @GetMapping("/search/{searchTerm}")
    @ResponseBody
    public List<OrderModel> searchOrders(@PathVariable(name="searchTerm") String searchTerm){
        //searchOrders
        List<OrderModel> orders = service.searchOrders(searchTerm);
        return orders;

    }

    @PostMapping("/")
    public long addOrder(@RequestBody OrderModel model){
        //createOrder
        return service.addOne(model);
    }

    @GetMapping("/{id}")
    public OrderModel getById(@PathVariable(name="id") long id){
        //getOneOrder
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteOne(@PathVariable(name="id") long id){
        //deleteOne
        return service.deleteOne(id);
    }

    @PutMapping("/update/{id}")
    public OrderModel update(@RequestBody OrderModel model, @PathVariable(name="id") long id){
        //updateOne
        return service.updateOne(id,model);
    }
}
