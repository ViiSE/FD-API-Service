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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.test.AttributesRepositoryTestImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class AttributesRepositoryTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private AttributesRepository attributesRepository;

    @BeforeClass
    public void setUpClass() {
        attributesRepository = new AttributesRepositoryTestImpl();
    }

    @Test
    public void readProducts() throws JsonProcessingException {
        testBegin("AttributesRepository", "readAttributes()");

        try {
            Attributes attributes = attributesRepository.read();
            System.out.println("Attributes:");
            String pojo = mapper.writeValueAsString(attributes.formForSend());
            assertTrue(pojo.contains("\"attributes\":"));
            System.out.println(pojo);
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("AttributesRepository", "readAttributes()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
