package com.junauto.practicespringlogin.services;

import com.junauto.practicespringlogin.data.OrdersDataAccessInterface;
import com.junauto.practicespringlogin.models.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



public class OrderBusinessService implements OrdersBusinessServiceInterface {
//business service level
    @Autowired
        @Qualifier("ordersDataServiceForRepository")
    OrdersDataAccessInterface<OrderModel> ordersDAO;
    @Override
    public void test() {
        System.out.println("OrderBusinessService is working");
    }

    @Override
    public OrderModel getById(long id) {

        return ordersDAO.getById(id);
    }

    @Override
    public List<OrderModel> getOrders() {

        return ordersDAO.getOrders();
    }

    @Override
    public List<OrderModel> searchOrders(String searchTerm) {
        return ordersDAO.searchOrders(searchTerm);
    }

    @Override
    public long addOne(OrderModel newOrder) {
        return ordersDAO.addOne(newOrder);
    }

    @Override
    public boolean deleteOne(long id) {
        return ordersDAO.deleteOne(id);
    }

    @Override
    public OrderModel updateOne(long idToUpdate, OrderModel updateOrder) {
        return ordersDAO.updateOne(idToUpdate, updateOrder);
    }

    @Override
    public void init() {
        System.out.println("Init method of the Orders Business Service");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy method of the Orders Business Service");
    }
}
