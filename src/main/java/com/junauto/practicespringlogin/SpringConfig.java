package com.junauto.practicespringlogin;

import com.junauto.practicespringlogin.data.OrdersDataAccessInterface;
import com.junauto.practicespringlogin.data.OrdersDataService;
import com.junauto.practicespringlogin.data.OrdersDataServiceForRepository;
import com.junauto.practicespringlogin.services.OrderBusinessService;

import com.junauto.practicespringlogin.services.OrdersBusinessServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import groovy.lang.Singleton;

import javax.sql.DataSource;


@Configuration
public class SpringConfig {

    @Bean(name="ordersBusinessService", initMethod = "init", destroyMethod = "destroy")
    @RequestScope
    public OrdersBusinessServiceInterface getOrdersBusinessService()
    {
        return new OrderBusinessService();
    }

    @Autowired
    DataSource dataSource;

    @Bean(name="ordersDAO")
    @RequestScope
    public OrdersDataAccessInterface getDataService()
    {
        return new OrdersDataServiceForRepository(dataSource);
        //return new OrdersDataService();
    }
}
