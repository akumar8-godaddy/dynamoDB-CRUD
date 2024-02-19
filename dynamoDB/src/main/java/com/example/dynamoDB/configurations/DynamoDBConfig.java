package com.example.dynamoDB.configurations;


import com.example.dynamoDB.model.db.OrderEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.extensions.VersionedRecordExtension;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;


import javax.validation.constraints.NotNull;
import java.net.URI;

@Configuration
public class DynamoDBConfig {

    @NotNull
    @Value("${access_key_id}")
    private String access_key_id;

    @NotNull
    @Value("${secret_key}")
    private String secret_key;

    @NotNull
    @Value("${region}")
    private String region;


    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient() {
        final DynamoDbClient client = dynamoDbClient();
        final DynamoDbEnhancedClientExtension revisionNumberExtension =
                VersionedRecordExtension.builder().build();
        return DynamoDbEnhancedClient.builder()
                .extensions(revisionNumberExtension)
                .dynamoDbClient(client)
                .build();
    }
@Bean
public DynamoDbClient dynamoDbClient()
{
    return DynamoDbClient.builder()
            .endpointOverride(URI.create("https://dynamodb.us-east-1.amazonaws.com"))
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(access_key_id, secret_key)))
            .build();
}
    @Bean
    public DynamoDbTable<OrderEntity> orderTable(
            DynamoDbEnhancedClient enhancedClient) {
         final DynamoDbTable<OrderEntity> table =
                enhancedClient.table("Orders",
                        TableSchema.fromBean(OrderEntity.class));
        return table;
    }

}
