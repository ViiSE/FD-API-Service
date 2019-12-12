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

package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.*;

import java.util.ArrayList;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductProducerDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    private ProductProducer productProducer;

    private Product product;

    @BeforeClass
    public void setUpClass() {
        testBegin("ProductProducerDefault");
    }

    @Test(priority = 1)
    @Parameters({"id", "categoryId", "vendorId", "unitId", "name", "tax", "articul", "code"})
    public void getProductSimpleInstance(
            String id, String categoryId, String vendorId, String unitId, String name, short tax, String articul, String code) {
        testMethod("getProductSimpleInstance()");

        product = productProducer.getProductDefaultInstance(id, categoryId, vendorId, unitId, name, tax, articul, code);
        assertTrue(product instanceof ProductDefaultImpl, "Product: not a valid type!");
        System.out.println("Instance: " + product);
    }

    @Test(priority = 2)
    public void getProductWithAttributesInstance() {
        testMethod("getProductWithAttributesInstance()");

        Product prWA = productProducer.getProductWithAttributesInstance(product, new AttributesDefaultImpl(new ArrayList<>()));
        assertTrue(prWA instanceof ProductWithAttributesImpl, "Product: not a valid type!");
        System.out.println("Instance: " + prWA);
    }

    @Test(priority = 3)
    public void getProductWithBalancesInstance() {
        testMethod("getProductWithBalancesInstance()");

        Product prB = productProducer.getProductWithBalancesInstance(product, new BalancesDefaultImpl(new ArrayList<>()));
        assertTrue(prB instanceof ProductWithBalancesImpl, "Product: not a valid type!");
        System.out.println("Instance: " + prB);
    }

    @Test(priority = 4)
    public void getProductWithFullDescriptionInstance() {
        testMethod("getProductWithFullDescriptionInstance()");

        Product prFD = productProducer.getProductWithFullDescriptionInstance(product, "Full description");
        assertTrue(prFD instanceof ProductWithFullDescriptionImpl, "Product: not a valid type!");
        System.out.println("Instance: " + prFD);
    }

    @Test(priority = 5)
    public void getProductWithShortDescriptionInstance() {
        testMethod("getProductWithShortDescriptionInstance()");

        Product prSD = productProducer.getProductWithShortDescriptionInstance(product, "Sh des");
        assertTrue(prSD instanceof ProductWithShortDescriptionImpl, "Product: not a valid type!");
        System.out.println("Instance: " + prSD);
    }

    @Test(priority = 6)
    public void getProductWithPricesInstance() {
        testMethod("getProductWithPricesInstance()");

        Product prPr = productProducer.getProductWithPricesInstance(product, new PricesDefaultImpl(new ArrayList<>()));
        assertTrue(prPr instanceof ProductWithPricesImpl, "Product: not a valid type!");
        System.out.println("Instance: " + prPr);
    }

    @Test(priority = 7)
    public void getProductWithStatusesInstance() {
        testMethod("getProductWithStatusesInstance()");

        Product prSt = productProducer.getProductWithStatusesInstance(product, new StatusesDefaultImpl(new ArrayList<>()));
        assertTrue(prSt instanceof ProductWithStatusesImpl, "Product: not a valid type!");
        System.out.println("Instance: " + prSt);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("ProductProducerDefault");
    }
}
