package com.example.dynamoDB.model.db;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@Data
@Builder
@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    private String orderID;
    private String productID;
    private String customerID;
    private String customerName;
    private String customerAddress;
    private Integer subtotal;

    @DynamoDbPartitionKey
    public String getOrderID()
    {
        return this.orderID;
    }
    @DynamoDbSortKey
    public String getCustomerID()
    {
        return this.customerID;
    }
//    @DynamoDbSecondaryPartitionKey(indexNames = {"customerName-index"})
//    public String getCustomerId() {
//        return this.customerID;
//    }
}
