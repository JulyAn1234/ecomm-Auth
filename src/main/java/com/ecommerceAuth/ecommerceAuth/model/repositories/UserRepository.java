package com.ecommerceAuth.ecommerceAuth.model.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ecommerceAuth.ecommerceAuth.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository{

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public User save(User user) {
        System.out.println("Llegue");
        dynamoDBMapper.save(user);
        return user;
    }

    public Optional<User> getUserByUsername(String username) {
        User user = null;
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":username", new AttributeValue().withS(username));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("username = :username")
                .withExpressionAttributeValues(eav);

        try {
            List<User> useResult = dynamoDBMapper.scan(User.class, scanExpression);
            if (!useResult.isEmpty()) {
                user = useResult.get(0);
                System.out.println("User found: " + user.getUsername());
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            System.err.println("Error during DynamoDB scan:");
            e.printStackTrace(); // Print stack trace for debugging
        }

        return Optional.ofNullable(user);
    }
}