package com.vg.cube.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.vg.cube.entity.PrincipalUser;
import com.vg.cube.service.DataCrudService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private DataCrudService crudService;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public PrincipalUser auth(@RequestParam(value = "userName", required = true) String userName,
            @RequestParam(value = "password", required = true) String password) {

        PrincipalUser user = crudService.createUser(new PrincipalUser(userName, password));
        LOGGER.info("User Created : user Id {}", user.getId());

        return user;
    }

    /**
     * local db running at C:\VG\RnD\dynamodb_local_latest .Run by exe-ing java - Djava.library.path=. -jar DynamoDBLocal.jar
     */
    @PostConstruct
    public void setup() {
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        try {
            DeleteTableRequest tableRequestD = dynamoDBMapper.generateDeleteTableRequest(PrincipalUser.class);
            amazonDynamoDB.deleteTable(tableRequestD);
        } catch (ResourceNotFoundException ex) {
            LOGGER.error("Table has not been created yet", ex.getMessage());
        }

        CreateTableRequest tableRequestC = dynamoDBMapper.generateCreateTableRequest(PrincipalUser.class);
        tableRequestC.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequestC);
        getTableInformation();
    }

    public void getTableInformation() {
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

        TableDescription tableDescription = dynamoDB.getTable("User").describe();
        LOGGER.info("\nName: {}\nStatus: {}\nProvisioned Throughput (read capacity units/sec): {}\n"
                + "Provisioned Throughput (write capacity units/sec): {}\n", tableDescription.getTableName(), tableDescription.getTableStatus(),
                tableDescription.getProvisionedThroughput().getReadCapacityUnits(), tableDescription.getProvisionedThroughput()
                        .getWriteCapacityUnits());
    }
}
