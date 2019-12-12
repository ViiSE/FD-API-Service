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

package ru.fd.api.service.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.exception.RepositoryException;
import test.repository.StatusesRepositoryTestImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class StatusesRepositoryTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private StatusesRepository statusesRepository;

    @BeforeClass
    public void setUpClass() {
        statusesRepository = new StatusesRepositoryTestImpl();
    }

    @Test
    public void readStatuses() throws JsonProcessingException {
        testBegin("StatusesRepository", "readStatuses()");

        try {
            Statuses statuses = statusesRepository.readStatuses();
            System.out.println("Statuses:");
            String pojo = mapper.writeValueAsString(statuses.formForSend());
            assertTrue(pojo.contains("\"statuses\":"));
            System.out.println(pojo);

        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("StatusesRepository", "readStatuses()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
