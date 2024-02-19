package com.example.dynamoDB.controller;

import com.example.dynamoDB.model.db.OrderEntity;
import com.example.dynamoDB.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
@SuppressWarnings("checkstyle:MissingJavadocMethod")
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/orderID/{orderID}/customerID/{customerID}")
    public ResponseEntity<Iterable<OrderEntity>> getDiscount(@PathVariable String orderID,
                                                             @PathVariable String customerID) {
        return ResponseEntity.ok(orderService.getOrder(orderID,customerID));
    }

    @PutMapping("/update")
    public ResponseEntity<OrderEntity> updateOrder(@RequestBody OrderEntity order)
    {
        return ResponseEntity.ok(orderService.updateOrder(order));
    }

    @DeleteMapping("/delete/orderID/{orderID}/customerID/{customerID}")
    public ResponseEntity<String> delete(@PathVariable String orderID , @PathVariable String customerID)
    {
        orderService.deleteOrder(orderID,customerID);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/addOrder")
    public ResponseEntity<String> addOrder(@RequestBody OrderEntity order)
    {
        orderService.addOrder(order);
        return ResponseEntity.ok("Order added");
    }



}
