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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderSimpleImpl;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;

import javax.persistence.Access;

import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsRepositoryWithChangedBalancesAndOrderIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("productsRepositoryWithChangedBalancesAndOrder")
    private ProductsRepositoryDecorative<Order> pRepo;

    private Order order;

    @BeforeClass
    public void setUpClass() {
        order = new OrderSimpleImpl(0L, (short) 0);
        writeTestTime("ProductsRepositoryWithChangedBalancesAndOrder");
    }

    @Test
    public void readProducts() {
        testBegin("ProductsRepositoryWithChangedBalancesAndOrderIntegration", "read()");

        try {
            Products products = pRepo.read(order);
            assertNotNull(products, "Products is null!");
            System.out.println("DONE! ");
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("ProductsRepositoryWithChangedBalancesAndOrderIntegration", "read()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
        writeTestTime(tr);
    }
}
