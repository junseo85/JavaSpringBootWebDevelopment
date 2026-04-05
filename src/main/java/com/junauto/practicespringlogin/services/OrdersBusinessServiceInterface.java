package com.junauto.practicespringlogin.services;

import com.junauto.practicespringlogin.models.OrderModel;

import java.util.List;

public interface OrdersBusinessServiceInterface {
    //making CRUD
    public void test();
    public void init();
    public void destroy();

    public OrderModel getById(long id);
    public List<OrderModel> getOrders();
    public List<OrderModel> searchOrders(String searchTerm);

    public long addOne(OrderModel newOrder);

    public boolean deleteOne(long id);

    public OrderModel updateOne(long idToUpdate, OrderModel updateOrder);


    //future methods

    //searchOrders(String searchTerm)

    //addOrder(OrderModel newO)

    //deleteOrder(Long id)

    //updateOrder(OrderModel updateMe)

    //getOneOrder(Long id)
}
