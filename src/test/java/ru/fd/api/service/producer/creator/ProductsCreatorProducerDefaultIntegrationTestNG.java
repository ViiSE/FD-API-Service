/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service.producer.creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.SQLQueryCreatorService;
import ru.fd.api.service.creator.OrderProductsCreatorDefaultImpl;
import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.creator.ProductsCreatorDefaultImpl;
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.producer.repository.processor.ProductsRepositoryProcessorsProducer;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsCreatorProducerDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    private ProductsCreatorProducer productsCreatorProducer;

    @Autowired private ProductsRepositoryProcessorsProducer productsRepoProsProducer;
    @Autowired private ProductsRepositoryProducer productsRepoProducer;
    @Autowired private ProductProducer productProducer;
    @Autowired private ProductsProducer productsProducer;
    @Autowired private SQLQueryCreatorService sqlQueryCreatorService;
    @Autowired private BalanceProducer balanceProducer;
    @Autowired private BalancesProducer balancesProducer;
    @Autowired private PriceProducer priceProducer;
    @Autowired private PricesProducer pricesProducer;

    @Test
    public void getProductsCreatorDefaultInstance() {
        testBegin("ProductsCreatorProducerDefault", "getProductsCreatorDefaultInstance()");

        ProductsCreator productsCreator = productsCreatorProducer.getProductsCreatorDefaultInstance(
                productsRepoProsProducer.getProductsRepositoryProcessorsSingletonImpl(
                        productsRepoProducer,
                        productProducer,
                        sqlQueryCreatorService.sqlQueryCreatorFromFileString(),
                        balanceProducer,
                        balancesProducer,
                        priceProducer,
                        pricesProducer),
                new ArrayList<>());
        assertTrue(productsCreator instanceof ProductsCreatorDefaultImpl, "ProductsCreator: not a valid type!");
        System.out.println("Instance: " + productsCreator);

        testEnd("ProductsCreatorProducerDefault", "getProductsCreatorDefaultInstance()");
    }

    @Test
    public void getOrderProductsCreatorDefaultInstance() {
        testBegin("ProductsCreatorProducerDefault", "getOrderProductsCreatorDefaultInstance()");

        ProductsCreator productsCreator = productsCreatorProducer.getOrderProductsCreatorDefaultInstance(
                new OrderPojo(
                        1,
                        "cId1",
                        new CustomerPojo(),
                        new DeliveryPojo((short) 0, "cId1", "addr"),
                        (short) 0,
                        LocalDateTime.now()),
                productsProducer,
                productProducer);
        assertTrue(productsCreator instanceof OrderProductsCreatorDefaultImpl, "OrderProductsCreator: not a valid type!");
        System.out.println("Instance: " + productsCreator);

        testEnd("ProductsCreatorProducerDefault", "getOrderProductsCreatorDefaultInstance()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
