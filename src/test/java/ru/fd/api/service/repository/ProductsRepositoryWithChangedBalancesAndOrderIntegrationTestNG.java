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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsRepositoryWithChangedBalancesAndOrderIntegrationTestNG extends AbstractTestNGSpringContextTests {

    private ProductsRepository productsRepository;

    @Autowired private ProductsRepositoryProducer productsRepositoryProducer;
    @Autowired private ProductProducer productProducer;
    @Autowired private ProductsProducer productsProducer;
    @Autowired private BalanceProducer balanceProducer;
    @Autowired private BalancesProducer balancesProducer;
    @Autowired private SQLQueryCreator<String, String> sqlQueryCreator;

    @BeforeClass
    public void setUpClass() {
        Order order = new OrderSimpleImpl(0L, (short) 0);

        productsRepository =
                productsRepositoryProducer.getProductsRepositoryWithChangedBalancesAndOrderInstance(
                        order,
                        productProducer,
                        productsProducer,
                        balanceProducer,
                        balancesProducer,
                        sqlQueryCreator);
        writeTestTime("ProductsRepositoryWithChangedBalancesAndOrder");
    }

    @Test
    public void readProducts() {
        testBegin("ProductsRepositoryWithChangedBalancesAndOrderIntegration", "readProducts()");

        try {
            Products products = productsRepository.read();
            assertNotNull(products, "Products is null!");
            System.out.println("DONE! ");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("ProductsRepositoryWithChangedBalancesAndOrderIntegration", "readProducts()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
