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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.repository.UnitsRepository;
import ru.fd.api.service.repository.UnitsRepositoryDefaultImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

@SpringBootTest(classes = ApiServiceApplication.class)
public class UnitsRepositoryProducerDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    private UnitsRepositoryProducer unitsRepositoryProducer;

    @Test
    public void getUnitsRepositoryDefaultInstance() {
        testBegin("UnitsRepositoryProducerDefault", "getUnitsRepositoryDefaultInstance()");

        UnitsRepository unRepo = unitsRepositoryProducer.getUnitsRepositoryDefaultInstance();
        assertTrue(unRepo instanceof UnitsRepositoryDefaultImpl,"UnitsRepository: not a valid type!");
        System.out.println("Instance: " + unRepo);

        testEnd("UnitsRepositoryProducerDefault", "getUnitsRepositoryDefaultInstance()");
    }
}
