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
import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.repository.AttributeGroupsRepository;
import test.repository.AttributeGroupsRepositoryExceptionTestImpl;
import test.repository.AttributeGroupsRepositoryTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class AttributeGroupsCreatorTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private AttributeGroupsCreator attributeGroupsCreator;

    @BeforeClass
    public void setUpClass() {
        AttributeGroupsRepository attributeGroupsRepository = new AttributeGroupsRepositoryTestImpl();
        attributeGroupsCreator = new AttributeGroupsCreatorDefaultImpl(attributeGroupsRepository);
        testBegin("AttributeGroupsCreator");
    }

    @Test(priority = 1)
    public void create() throws CreatorException, JsonProcessingException {
        testMethod("create()");

        AttributeGroups attributeGroups = attributeGroupsCreator.create();
        assertNotNull(attributeGroups, "AttributeGroups is null!");
        assertTrue(mapper.writeValueAsString(attributeGroups.formForSend()).contains("\"attribute_groups\""));
        System.out.println(mapper.writeValueAsString(attributeGroups.formForSend()));
    }

    @Test(priority = 2, expectedExceptions = CreatorException.class)
    public void create_exception() throws CreatorException {
        testMethod("create() [with exceptions]");

        AttributeGroupsRepository attributeGroupsRepository = new AttributeGroupsRepositoryExceptionTestImpl();
        attributeGroupsCreator = new AttributeGroupsCreatorDefaultImpl(attributeGroupsRepository);
        attributeGroupsCreator.create();
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("AttributeGroupsCreator");
    }
}
