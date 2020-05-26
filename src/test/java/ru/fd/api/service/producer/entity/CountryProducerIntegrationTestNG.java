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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Country;
import ru.fd.api.service.entity.CountryImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

@SpringBootTest(classes = ApiServiceApplication.class)
public class CountryProducerIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    private CountryProducer countryProducer;

    @Test
    @Parameters({"id", "name"})
    public void getCountryInstance(String id, String name) {
        testBegin(CountryProducerImpl.class, "getCountryInstance()");

        Country country = countryProducer.getCountryInstance(id, name);
        assertTrue(country instanceof CountryImpl, "Country: not a valid type!");
        System.out.println("Instance: " + country);

        testEnd(CountryProducerImpl.class, "getCountryInstance()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}