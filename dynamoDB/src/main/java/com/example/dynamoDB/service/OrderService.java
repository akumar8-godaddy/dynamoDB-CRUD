package com.example.dynamoDB.service;

import com.example.dynamoDB.Repository.OrderRepository;
import com.example.dynamoDB.model.db.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public Iterable<OrderEntity> getOrder(String orderID , String customerID)
    {
        Iterable<OrderEntity> response = orderRepository.getOrderByOrderIDandCustomerID(orderID,customerID);
        return response;
    }
    public void addOrder(OrderEntity order)
    {
        orderRepository.addOrder(order);
    }
    public void deleteOrder(String OrderID , String customerID)
    {
        orderRepository.deleteOrder(OrderID,customerID);
    }
    public OrderEntity updateOrder(OrderEntity order)
    {
        return orderRepository.updateOrder(order);
    }



}
