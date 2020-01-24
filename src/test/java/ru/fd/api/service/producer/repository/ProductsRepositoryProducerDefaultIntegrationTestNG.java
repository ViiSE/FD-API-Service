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

package ru.fd.api.service.producer.repository;

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
import ru.fd.api.service.repository.*;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ProductsRepositoryProducerDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("productsRepositoryProducerDefault")
    private ProductsRepositoryProducer productsRepositoryProducer;

    @BeforeClass
    public void setUpClass() {
        testBegin("ProductsRepositoryProducerDefault");
    }

    private ProductsRepository prRepo;

    @Test(priority = 1)
    public void getProductsRepositorySimpleInstance() {
        testMethod("getProductsRepositorySimpleInstance()");

        prRepo = productsRepositoryProducer.getProductsRepositorySimpleInstance(null, null);
        assertTrue(prRepo instanceof ProductsRepositorySimpleImpl,"ProductsRepository: not a valid type!");
        System.out.println("Instance: " + prRepo);
    }

    @Test(priority = 2)
    public void getProductsRepositoryWithAttributesInstance() {
        testMethod("getProductsRepositoryWithAttributesInstance()");

        ProductsRepository prRepoWA = productsRepositoryProducer.getProductsRepositoryWithAttributesInstance(
                prRepo ,
                null,
                null);
        assertTrue(prRepoWA instanceof ProductsRepositoryWithAttributesImpl,"ProductsRepository: not a valid type!");
        System.out.println("Instance: " + prRepoWA);
    }

    @Test(priority = 3)
    public void getProductsRepositoryWithBalancesInstance() {
        testMethod("getProductsRepositoryWithBalancesInstance()");

        ProductsRepository prRepoWB = productsRepositoryProducer.getProductsRepositoryWithBalancesInstance(
                prRepo ,
                null,
                null,
                null,
                null);
        assertTrue(prRepoWB instanceof ProductsRepositoryWithBalancesImpl,"ProductsRepository: not a valid type!");
        System.out.println("Instance: " + prRepoWB);
    }

    @Test(priority = 4)
    public void getProductsRepositoryWithFullDescriptionInstance() {
        testMethod("getProductsRepositoryWithFullDescriptionInstance()");

        ProductsRepository prRepoWFD = productsRepositoryProducer.getProductsRepositoryWithFullDescriptionInstance(
                prRepo,
                null,
                null);
        assertTrue(prRepoWFD instanceof ProductsRepositoryWithFullDescriptionImpl,"ProductsRepository: not a valid type!");
        System.out.println("Instance: " + prRepoWFD);
    }

    @Test(priority = 5)
    public void getProductsRepositoryWithShortDescriptionInstance() {
        testMethod("getProductsRepositoryWithShortDescriptionInstance()");

        ProductsRepository prRepoWSD = productsRepositoryProducer.getProductsRepositoryWithShortDescriptionInstance(
                prRepo,
                null,
                null);
        assertTrue(prRepoWSD instanceof ProductsRepositoryWithShortDescriptionImpl,"ProductsRepository: not a valid type!");
        System.out.println("Instance: " + prRepoWSD);
    }

    @Test(priority = 6)
    public void getProductsRepositoryWithPricesInstance() {
        testMethod("getProductsRepositoryWithPricesInstance()");

        ProductsRepository prRepoWP = productsRepositoryProducer.getProductsRepositoryWithPricesInstance(
                prRepo ,
                null,
                null,
                null,
                null);
        assertTrue(prRepoWP instanceof ProductsRepositoryWithPricesImpl,"ProductsRepository: not a valid type!");
        System.out.println("Instance: " + prRepoWP);
    }

    @Test(priority = 7)
    public void getProductsRepositoryWithStatusesInstance() {
        testMethod("getProductsRepositoryWithStatusesInstance()");

        ProductsRepository prRepoWS = productsRepositoryProducer.getProductsRepositoryWithStatusesInstance(
                prRepo ,
                null,
                null);
        assertTrue(prRepoWS instanceof ProductsRepositoryWithStatusesImpl,"ProductsRepository: not a valid type!");
        System.out.println("Instance: " + prRepoWS);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("ProductsRepositoryProducerDefault");
    }
}
