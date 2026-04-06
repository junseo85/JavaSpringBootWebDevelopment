package com.junauto.practicespringlogin.data;

import com.junauto.practicespringlogin.models.OrderModel;
import com.junauto.practicespringlogin.models.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository//giving data with methods
public class OrdersDataService implements OrdersDataAccessInterface<OrderModel>{

    //see application.properties file to get the detail on the mysql connection
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate; //package that simplifies the use of java database connections and helps to avoid common errors

    @Override
    public OrderModel getById(long id) {
        List<OrderModel> results = jdbcTemplate.query("SELECT * FROM ORDERS WHERE ID = ?", new OrdersMapper(),id);

        if(results.size()>0)
            return results.get(0);
        else
            return null;
    }

    @Override
    public List<OrderModel> getOrders() {
        List<OrderModel> results=jdbcTemplate.query("SELECT * FROM ORDERS", new OrdersMapper());
        return results;
    }

    @Override
    public List<OrderModel> searchOrders(String searchTerm) {
        List<OrderModel> results = jdbcTemplate.query("SELECT * FROM ORDERS WHERE PRODUCT_NAME LIKE ?", new OrdersMapper(), "%" + searchTerm + "%");
        return results;
    }

    @Override
    public long addOne(OrderModel newOrder) {
//        long result = jdbcTemplate.update("INSERT INTO ORDERS (ORDER_NUMBER, PRODUCT_NAME, PRICE, QTY) VALUES (?,?,?,?)",
//                newOrder.getOrderNo(), newOrder.getProductName(), newOrder.getPrice(), newOrder.getQuantity());
//        return result;
        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleInsert.withTableName("ORDERS").usingGeneratedKeyColumns("ID");

        Map<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("ORDER_NUMBER", newOrder.getOrderNo());
        parameters.put("PRODUCT_NAME", newOrder.getProductName());
        parameters.put("PRICE", newOrder.getPrice());
        parameters.put("QTY", newOrder.getQuantity());

        Number result = simpleInsert.executeAndReturnKey(parameters);

        return result.longValue();
    }

    @Override
    public boolean deleteOne(long id) {
        int result = jdbcTemplate.update("DELETE FROM ORDERS WHERE ID = ?", id);
        if(result>0)
            return true;
        else
            return false;
    }

    @Override
    public OrderModel updateOne(long idToUpdate, OrderModel updateOrder) {

        int result = jdbcTemplate.update("UPDATE ORDERS SET ORDER_NUMBER = ?, PRODUCT_NAME = ?, PRICE = ?, QTY = ? WHERE ID = ?",updateOrder.getOrderNo(), updateOrder.getProductName(), updateOrder.getPrice(), updateOrder.getQuantity(), idToUpdate);
        if (result >0){
            return updateOrder;
        }
        else
            return null;
    }
}
