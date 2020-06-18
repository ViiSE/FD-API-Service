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

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class DescriptionProducerIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    private DescriptionProducer descriptionProducer;

    @BeforeClass
    public void beforeClass() {
        testBegin(DescriptionProducerImpl.class);
    }

    @Test
    @Parameters({"shortDescription"})
    public void getDescriptionShortInstance(String shortDescription) {
        testMethod("getDescriptionShortInstance()");

        Description description = descriptionProducer.getDescriptionShortInstance(shortDescription);
        assertTrue(description instanceof DescriptionShortImpl, "Description: not a valid type!");
        System.out.println("Instance: " + description);
    }

    @Test
    @Parameters({"shortDescription", "fullDescription"})
    public void getDescriptionFullInstance(String shortDescription, String fullDescription) {
        testMethod("getDescriptionFullInstance()");

        Description description = descriptionProducer.getDescriptionFullInstance(
                descriptionProducer.getDescriptionShortInstance(shortDescription),
                fullDescription);
        assertTrue(description instanceof DescriptionFullImpl, "Description: not a valid type!");
        System.out.println("Instance: " + description);
    }

    @Test
    @Parameters({"shortDescription", "productId"})
    public void getDescriptionWithProductIdInstance(String shortDescription, String productId) {
        testMethod("getDescriptionWithProductIdInstance()");

        Description description = descriptionProducer.getDescriptionWithProductIdInstance(
                descriptionProducer.getDescriptionShortInstance(shortDescription),
                productId);
        assertTrue(description instanceof DescriptionWithProductIdImpl, "Description: not a valid type!");
        System.out.println("Instance: " + description);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void afterClass() {
        testEnd(DescriptionProducerImpl.class);
    }
}
