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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.*;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class AttributeProducerIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    private AttributeProducer attributeProducer;

    @BeforeClass
    public void setUpMethod() {
        testBegin(AttributeProducerImpl.class);
    }

    @Test
    public void getAttributeInstance() {
        testMethod("getAttributeInstance");

        Attribute attr = attributeProducer.getAttributeInstance("1", 1L, "attr");
        assertTrue(attr instanceof AttributeImpl, "Attribute: not a valid type!");
        System.out.println("Instance: " + attr);
    }

    @Test
    public void getProductAttributeInstance() {
        testMethod("getProductAttributeInstance");

        Attribute attr = attributeProducer.getProductAttributeInstance("1", "value");
        assertTrue(attr instanceof ProductAttributeImpl, "Attribute: not a valid type!");
        System.out.println("Instance: " + attr);
    }

    @Test
    public void getProductAttributeWithCodeAvardaInstance() {
        testMethod("getProductAttributeWithCodeAvardaInstance");

        Attribute attr = attributeProducer.getProductAttributeWithCodeAvardaInstance(
                attributeProducer.getProductAttributeInstance("1", "value"),
                "codeAvarda");
        assertTrue(attr instanceof ProductAttributeWithCodeAvardaImpl, "Attribute: not a valid type!");
        System.out.println("Instance: " + attr);
    }

    @Test
    public void getAttributeWithCodeAvardaInstance() {
        testMethod("getAttributeWithCodeAvardaInstance");

        Attribute attr = attributeProducer.getAttributeWithCodeAvardaInstance(
                attributeProducer.getAttributeInstance("1", 2L, "attr"),
                "codeAvarda");
        assertTrue(attr instanceof AttributeWithCodeAvardaImpl, "Attribute: not a valid type!");
        System.out.println("Instance: " + attr);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void shutdownClass() {
        testEnd(AttributeProducerImpl.class);
    }
}
