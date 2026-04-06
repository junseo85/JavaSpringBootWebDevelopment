package com.junauto.practicespringlogin.data;

import com.junauto.practicespringlogin.models.OrderEntity;
import com.junauto.practicespringlogin.models.OrderModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class OrdersDataServiceForRepository implements OrdersDataAccessInterface<OrderModel>{

    //need a data source
    @Autowired
    OrdersRepositoryInterface ordersRepository;

    private JdbcTemplate jdbcTemplate;

    public OrdersDataServiceForRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    ModelMapper modelMapper = new ModelMapper();
    @Override
    public OrderModel getById(long id) {
        OrderEntity entity = ordersRepository.findById(id).orElse(null);

        //OrderModel model = new OrderModel(entity.getId(), entity.getOrderNo(), entity.getProductName(), entity.getPrice(), entity.getQuantity());
        OrderModel model = modelMapper.map(entity, OrderModel.class); //mapping

        return model;
    }

    @Override
    public List<OrderModel> getOrders() {
        Iterable<OrderEntity> ordersEntity =ordersRepository.findAll();
        List<OrderModel> models = new ArrayList<OrderModel>();
        for (OrderEntity item: ordersEntity) {
            //add item to the list of ordermodel
            models.add(modelMapper.map(item, OrderModel.class));
        }
        return models;
    }

    @Override
    public List<OrderModel> searchOrders(String searchTerm) {
        Iterable<OrderEntity> entities = ordersRepository.findByProductNameContainingIgnoreCase(searchTerm);
        List<OrderModel> orders = new ArrayList<OrderModel>();
        for (OrderEntity entity: entities) {
            orders.add(modelMapper.map(entity, OrderModel.class));
        }
        return orders;
    }

    @Override
    public long addOne(OrderModel newOrder) {
//        OrderEntity entity = modelMapper.map(newOrder, OrderEntity.class);
//        OrderEntity result = ordersRepository.save(entity);
//        if(result == null){
//            return 0;
//
//        } else{
//            return result.getId();
//        }


            //System.out.println("Incoming model: " + newOrder);

            OrderEntity entity = modelMapper.map(newOrder, OrderEntity.class);
            //System.out.println("Mapped entity BEFORE save: " + entity);
            entity.setId(null);
            OrderEntity result = ordersRepository.save(entity);
            //System.out.println("Saved entity AFTER save: " + result);

            return result != null ? result.getId() : 0;


    }

    @Override
    public boolean deleteOne(long id) {
        ordersRepository.deleteById(id);
        return true;
    }

    @Override
    public OrderModel updateOne(long idToUpdate, OrderModel updateOrder) {
        OrderEntity entity = modelMapper.map(updateOrder, OrderEntity.class);
        OrderEntity result = ordersRepository.save(entity);
        OrderModel order = modelMapper.map(result,OrderModel.class);
        return order;
    }
}
