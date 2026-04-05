package com.junauto.practicespringlogin.models;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersMapper implements RowMapper<OrderModel>{
    @Override
    public OrderModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        //get the result set

        //create a new order object

        //return it.

        OrderModel order = new OrderModel(rs.getLong("ID"), rs.getString("ORDER_NUMBER"), rs.getString("PRODUCT_NAME"), rs.getFloat("PRICE"), rs.getInt("QTY"));

        return order;
    }
}
