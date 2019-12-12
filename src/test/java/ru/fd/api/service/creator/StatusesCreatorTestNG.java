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

package ru.fd.api.service.creator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.repository.StatusesRepository;
import test.repository.StatusesRepositoryExceptionTestImpl;
import test.repository.StatusesRepositoryTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class StatusesCreatorTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private StatusesCreator statusesCreator;

    @BeforeClass
    public void setUpClass() {
        StatusesRepository statusesRepository = new StatusesRepositoryTestImpl();
        statusesCreator = new StatusesCreatorDefaultImpl(statusesRepository);
        testBegin("StatusesCreator");
    }

    @Test(priority = 1)
    public void create() throws CreatorException, JsonProcessingException {
        testMethod("create()");

        Statuses statuses = statusesCreator.create();
        assertNotNull(statuses, "Statuses is null!");
        assertTrue(mapper.writeValueAsString(statuses.formForSend()).contains("\"statuses\""));
        System.out.println(mapper.writeValueAsString(statuses.formForSend()));
    }

    @Test(priority = 2, expectedExceptions = CreatorException.class)
    public void create_exception() throws CreatorException {
        testMethod("create() [with exceptions]");

        StatusesRepository statusesRepository = new StatusesRepositoryExceptionTestImpl();
        statusesCreator = new StatusesCreatorDefaultImpl(statusesRepository);
        statusesCreator.create();
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("StatusesCreator");
    }
}
