package com.example.dynamoDB.Repository;

import com.example.dynamoDB.model.db.OrderEntity;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository{

//    @Getter
//    private final DynamoDbIndex<OrderEntity> discountIdIndex;

    @Getter
    private final DynamoDbTable<OrderEntity> table;

    OrderRepositoryImpl(DynamoDbTable<OrderEntity> table)
    {
//        this.discountIdIndex  = discountIdIndex;
        this.table = table;
    }
    public Iterable<OrderEntity> getOrderByOrderIDandCustomerID(String orderID, String customerID)
    {
        QueryConditional queryConditional = QueryConditional.keyEqualTo(createKey(orderID));
        Iterable<OrderEntity> results = table.query(r -> r.queryConditional(queryConditional)).items();
        return results;
    }
    public void deleteOrder(String orderID,String customerID)
    {
        table.deleteItem(createKey(orderID,customerID));
    }
    public OrderEntity updateOrder(OrderEntity dbRecord) {
        return table.updateItem(dbRecord);
    }
    public void addOrder(OrderEntity order)
    {
        table.putItem(order);
    }
    public QueryEnhancedRequest createQueryEnhancedRequest(QueryConditional query,
                                                           Integer limit) {
        return QueryEnhancedRequest
                .builder()
                .queryConditional(query)
                .limit(limit)
                .build();
    }
    public QueryConditional createQueryConditional(String partitionKey) {
        return QueryConditional
                .keyEqualTo(createKey(partitionKey));
    }
    public Key createKey(String partitionKey) {
        return Key.builder()
                .partitionValue(partitionKey)
                .build();
    }
    public Key createKey(String partitionKey, String sortKey) {
        return Key.builder()
                .partitionValue(partitionKey)
                .sortValue(sortKey)
                .build();
    }


}
