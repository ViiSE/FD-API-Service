/*
 * Copyright 2020 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static test.message.TestMessage.*;

// TODO: 24.01.2020 CREATE NOT SUPPORTED TESTS
@SpringBootTest(classes = ApiServiceApplication.class)
public class OrderRepositoryDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired private OrderProducer oP;
    @Autowired private DeliveryProducer dP;
    @Autowired private CustomerProducer cP;
    @Autowired private ProductsProducer psP;
    @Autowired private ProductProducer pP;
    @Autowired private OrderRepository<OrderResponse, Order> orderRepo;

    private Order order;

    @BeforeClass
    public void setUpClass() {
        order = oP.getOrderWithCommentInstance(
                oP.getOrderWithProductsInstance(
                        oP.getOrderWithCustomerInstance(
                                oP.getOrderWithDeliveryInstance(
                                        oP.getOrderWithDateTimeInstance(
                                                oP.getOrderWithPayTypeIdInstance(
                                                        oP.getOrderWithCityIdInstance(
                                                                oP.getOrderSimpleInstance(
                                                                        0,
                                                                        (short) 0),
                                                                0),
                                                        (short) 0),
                                                LocalDateTime.now()),
                                        dP.getDeliveryWithDateInstance(
                                                dP.getDeliveryWithTimeIdInstance(
                                                        dP.getDeliveryWithDepartmentIdInstance(
                                                                dP.getDeliverySimpleInstance(
                                                                        (short) 0,
                                                                        101,
                                                                        "ул. Пушкина, 145Б"),
                                                                "100"),
                                                        (short) 0),
                                                LocalDate.now())),
                                cP.getCustomerFromCompanyInstance(
                                        cP.getCustomerWithNameInstance(
                                                cP.getCustomerWithEmailInstance(
                                                        cP.getCustomerWithPhoneNumberInstance(
                                                                cP.getCustomerSimpleInstance((short) 0),
                                                                "85551111111"),
                                                        "example@example.com"),
                                                "John Doe"),
                                        "22505",
                                        "43122")),
                        psP.getOrderProductsDefaultInstance(
                                new ArrayList<>() {{
                                    add(pP.getOrderProductSimpleInstance("id1", 10));
                                    add(pP.getOrderProductSimpleInstance("id2", 20));
                                }})),
                "Коментарий");
        testBegin("OrderRepositoryDefault");
    }

    @Test(priority = 1)
    public void insert() throws RepositoryException, JsonProcessingException {
        testMethod("insert()");

        OrderResponse orderResp = orderRepo.insert(order);
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(orderResp.formForSend()));
    }

    @Test(priority = 2, expectedExceptions = RepositoryException.class, expectedExceptionsMessageRegExp = "Not supported yet.")
    public void read() throws RepositoryException {
        testMethod("read()");

        orderRepo.read(0);
    }

    @Test(priority = 3, expectedExceptions = RepositoryException.class, expectedExceptionsMessageRegExp = "Not supported yet.")
    public void readAll() throws RepositoryException {
        testMethod("readAll()");

        orderRepo.readAll();
    }

    @Test(priority = 4, expectedExceptions = RepositoryException.class, expectedExceptionsMessageRegExp = "Not supported yet.")
    public void readFirst() throws RepositoryException {
        testMethod("readFirst()");

        orderRepo.readFirst(5);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
//        writeTestTime(tr);
    }

    @AfterClass
    public void shutdownClass() {
        testEnd("OrderRepositoryDefault");
    }
}
