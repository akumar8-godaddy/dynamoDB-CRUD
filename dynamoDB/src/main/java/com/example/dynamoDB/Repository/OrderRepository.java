package com.example.dynamoDB.Repository;

import com.example.dynamoDB.model.db.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderRepository {

    Iterable<OrderEntity> getOrderByOrderIDandCustomerID(String orderID, String customerID);
    void deleteOrder(String orderID,String customerID);
    void addOrder(OrderEntity order);
    OrderEntity updateOrder(OrderEntity order);

}
