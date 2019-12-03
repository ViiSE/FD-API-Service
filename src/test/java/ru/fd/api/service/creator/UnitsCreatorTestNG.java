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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.entity.Units;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.repository.StatusesRepository;
import ru.fd.api.service.repository.UnitsRepository;
import test.repository.StatusesRepositoryExceptionTestImpl;
import test.repository.StatusesRepositoryTestImpl;
import test.repository.UnitsRepositoryExceptionTestImpl;
import test.repository.UnitsRepositoryTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class UnitsCreatorTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private UnitsCreator unitsCreator;

    @BeforeClass
    public void setUpClass() {
        UnitsRepository unitsRepository = new UnitsRepositoryTestImpl();
        unitsCreator = new UnitsCreatorDefaultImpl(unitsRepository);
        testBegin("UnitsCreator");
    }

    @Test(priority = 1)
    public void create() throws CreatorException, JsonProcessingException {
        testMethod("create()");

        Units units = unitsCreator.create();
        assertNotNull(units, "Units is null!");
        assertTrue(mapper.writeValueAsString(units.formForSend()).contains("\"units\""));
        System.out.println(mapper.writeValueAsString(units.formForSend()));
    }

    @Test(priority = 2, expectedExceptions = CreatorException.class)
    public void create_exception() throws CreatorException {
        testMethod("create() [with exceptions]");

        UnitsRepository unitsRepository = new UnitsRepositoryExceptionTestImpl();
        unitsCreator = new UnitsCreatorDefaultImpl(unitsRepository);
        unitsCreator.create();
    }

    @AfterClass
    public void teardownClass() {
        testEnd("UnitsCreator");
    }
}
