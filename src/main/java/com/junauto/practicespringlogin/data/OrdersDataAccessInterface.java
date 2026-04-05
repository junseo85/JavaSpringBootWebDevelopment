package com.junauto.practicespringlogin.data;

import com.junauto.practicespringlogin.models.OrderModel;

import java.util.List;

public interface OrdersDataAccessInterface {
    //blueprint

    public OrderModel getById(long id); //getOneOrder

    public List<OrderModel> getOrders(); //getAllOrders

    public List<OrderModel> searchOrders(String searchTerm); // searchOrders

    public long addOne(OrderModel newOrder); //createOrder

    public boolean deleteOne(long id); // deleteOne

    public OrderModel updateOne(long idToUpdate, OrderModel updateOrder); //updateOne
}
