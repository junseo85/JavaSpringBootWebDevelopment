package com.junauto.practicespringlogin.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table(name ="ORDERS")
public class OrderEntity {
    //OrderEntity is based on OrderModel
    //It's purpose is to connect the OrderModel to the Orders table in the database.


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    Long id =0L;
    @Column(name = "ORDER_NUMBER")
    String orderNo = "";
    @Column(name = "PRODUCT_NAME")
    String productName = "";
    @Column(name = "PRICE")
    float price = 0;
    @Column(name = "QTY")
    int quantity = 0;

    //we need a parameterless constructor for the mapping library we will use later.
    public OrderEntity() {

    }
    public OrderEntity(Long id, String orderNo, String productName, float price, int quantity) {
        this.id = id;
        this.orderNo = orderNo;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
