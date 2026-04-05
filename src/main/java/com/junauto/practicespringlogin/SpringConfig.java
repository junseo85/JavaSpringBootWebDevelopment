package com.junauto.practicespringlogin;

import com.junauto.practicespringlogin.services.OrderBusinessService;

import com.junauto.practicespringlogin.services.OrdersBusinessServiceInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class SpringConfig {

    @Bean(name="ordersBusinessService", initMethod = "init", destroyMethod = "destroy")
    @RequestScope
    public OrdersBusinessServiceInterface getOrdersBusinessService()
    {
        return new OrderBusinessService();
    }
}
