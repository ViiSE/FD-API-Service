/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.exception.RepositoryException;
import test.util.TestUtils;

import java.util.List;

import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class OrderRepositoryChangedBalancesIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("orderRepositoryChangedBalances")
    private OrderRepository<Void, Order> orderRepo;

    private final ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();

    @BeforeClass
    public void setUpClass() {
        testBegin("OrderRepositoryChangedBalancesIntegration");
    }

    @Test(priority = 1, expectedExceptions = RepositoryException.class, expectedExceptionsMessageRegExp = "Cannot insert in ChangedBalances instance")
    public void insert() throws RepositoryException {
        testMethod("insert()");

        orderRepo.insert();
    }

    @Test(priority = 2)
    public void read() throws RepositoryException, JsonProcessingException {
        testMethod("read()");

        Order order = orderRepo.read(0);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));
    }

    @Test(priority = 3)
    public void readAll() throws RepositoryException, JsonProcessingException {
        testMethod("readAll()");

        List<Order> orders = orderRepo.readAll();
        System.out.println("List of orders:");
        for(Order order: orders)
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));
    }

    @Test(priority = 4)
    public void readFirst() throws RepositoryException, JsonProcessingException {
        testMethod("readFirst()");

        List<Order> orders = orderRepo.readFirst(5);
        System.out.println("List of orders:");
        for(Order order: orders)
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
//        writeTestTime(tr);
    }

    @AfterClass
    public void shutdownClass() {
        testEnd("OrderRepositoryChangedBalancesIntegration");
    }
}
