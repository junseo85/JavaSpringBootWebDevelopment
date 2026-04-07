package com.junauto.practicespringlogin.controllers;

import com.junauto.practicespringlogin.models.OrderModel;
import com.junauto.practicespringlogin.models.SearchModel;
import com.junauto.practicespringlogin.services.OrderBusinessService;

import com.junauto.practicespringlogin.services.OrdersBusinessServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/showNewOrderForm")
    public String showNewFOrm(Model model){
        model.addAttribute("order",new OrderModel());
        return "addNewOrderForm.html";
    }

    @PostMapping("/addNew")
    public String addnew(@Valid OrderModel newOrder, BindingResult bindingResult, Model model){
        newOrder.setId(null);
        //add to the database

        service.addOne(newOrder);
        //get all orders from database
        List<OrderModel> orders = service.getOrders();
        //show all orders page.
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/showSearchForm")
    public String showSearchForm(Model model){
        model.addAttribute("searchModel",new SearchModel());
        return "searchForm.html";
    }

    @PostMapping("/search")
    public String search(@Valid SearchModel searchModel, BindingResult bindingResult, Model model){
        String searchTerm = searchModel.getSearchTerm();
        List<OrderModel> orders = service.searchOrders(searchTerm);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model){
        //generate some orders
        //this version of the app will send a hard-coded list of orders


        List<OrderModel> orders = service.getOrders();


        model.addAttribute("title","Here is what I want to do this summer");
        model.addAttribute("orders", orders);
        return  "ordersAdmin.html";
    }

    @PostMapping("/editForm")
    public String displayEditForm(OrderModel orderModel, Model model){
        //Display new order form
        model.addAttribute("title", "Edit order");
        model.addAttribute("orderModel", orderModel);
        return "editForm.html";
    }

    @PostMapping("/doUpdate")
    //process a request from the AddOrderForm. Add a new order to the database. Show all orders.
    public String updateOrder(@Valid OrderModel order, BindingResult bindingResult, Model model){
        //add the new order
        service.updateOne(order.getId(), order);
        //get updated list of all the orders
        List<OrderModel> orders = service.getOrders();

        //display all orders
        model.addAttribute("orders", orders);

        return "ordersAdmin.html";
    }

    @PostMapping("/delete/")
    public String deleteOrder(@Valid OrderModel order, BindingResult bindingResult, Model model){
        service.deleteOne(order.getId());
        //get updated list of all the orders
        List<OrderModel> orders = service.getOrders();
        //display all orders
        model.addAttribute("orders", orders);
        return "ordersAdmin.html";
    }

    @GetMapping("/spa")
    public String showSPApage(Model model){
        return "ordersSpa.html";
    }

    @GetMapping("/test")
    public String test(){
        return "orders";
    }
}
