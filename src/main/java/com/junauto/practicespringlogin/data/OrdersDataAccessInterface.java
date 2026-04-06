package com.junauto.practicespringlogin.data;

import com.junauto.practicespringlogin.models.OrderModel;

import java.util.List;

public interface OrdersDataAccessInterface <T>{
    //blueprint

    public T getById(long id); //getOneOrder

    public List<T> getOrders(); //getAllOrders

    public List<T> searchOrders(String searchTerm); // searchOrders

    public long addOne(T newOrder); //createOrder

    public boolean deleteOne(long id); // deleteOne

    public T updateOne(long idToUpdate, T updateOrder); //updateOne
}
