package com.vg.cube.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@PropertySource(value = "classpath:application.properties")
@EnableDynamoDBRepositories(basePackages = "com.vg.cube.dao")
public class AwsDynamoDbConfiguration {

    @Value("${amazon.dynamodb.endpoint}")
    private String awsDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String accessKey;

    @Value("${amazon.aws.secretkey}")
    private String secretKey;

    @Value("${cloud.aws.region}")
    private String region;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        EndpointConfiguration endpointConfiguration = new EndpointConfiguration(awsDynamoDBEndpoint, Regions.fromName(region).getName());
                
       return AmazonDynamoDBClientBuilder.standard()
               // .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withEndpointConfiguration(endpointConfiguration)
                .build();
    }
}
