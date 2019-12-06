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
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.repository.AttributesRepository;
import test.repository.AttributesRepositoryExceptionTestImpl;
import test.repository.AttributesRepositoryTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class AttributesCreatorTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private AttributesCreator attributesCreator;

    @BeforeClass
    public void setUpClass() {
        AttributesRepository attributesRepository = new AttributesRepositoryTestImpl();
        attributesCreator = new AttributesCreatorDefaultImpl(attributesRepository);
        testBegin("AttributesCreator");
    }

    @Test(priority = 1)
    public void create() throws CreatorException, JsonProcessingException {
        testMethod("create()");

        Attributes attributes = attributesCreator.create();
        assertNotNull(attributes, "Attributes is null!");
        assertTrue(mapper.writeValueAsString(attributes.formForSend()).contains("\"attributes\""));
        System.out.println(mapper.writeValueAsString(attributes.formForSend()));
    }

    @Test(priority = 2, expectedExceptions = CreatorException.class)
    public void create_exception() throws CreatorException {
        testMethod("create() [with exceptions]");

        AttributesRepository attributesRepository = new AttributesRepositoryExceptionTestImpl();
        attributesCreator = new AttributesCreatorDefaultImpl(attributesRepository);
        attributesCreator.create();
    }

    @AfterClass
    public void teardownClass() {
        testEnd("AttributesCreator");
    }
}
