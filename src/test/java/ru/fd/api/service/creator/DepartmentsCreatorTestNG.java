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
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.repository.CategoriesRepository;
import ru.fd.api.service.repository.DepartmentsRepository;
import test.repository.CategoriesRepositoryExceptionTestImpl;
import test.repository.CategoriesRepositoryTestImpl;
import test.repository.DepartmentsRepositoryExceptionTestImpl;
import test.repository.DepartmentsRepositoryTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class DepartmentsCreatorTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private DepartmentsCreator departmentsCreator;

    @BeforeClass
    public void setUpClass() {
        DepartmentsRepository departmentsRepository = new DepartmentsRepositoryTestImpl();
        departmentsCreator = new DepartmentsCreatorDefaultImpl(departmentsRepository);
        testBegin("DepartmentsCreator");
    }

    @Test(priority = 1)
    public void create() throws CreatorException, JsonProcessingException {
        testMethod("create()");

        Departments departments = departmentsCreator.create();
        assertNotNull(departments, "Departments is null!");
        assertTrue(mapper.writeValueAsString(departments.formForSend()).contains("\"departments\""));
        System.out.println(mapper.writeValueAsString(departments.formForSend()));
    }

    @Test(priority = 2, expectedExceptions = CreatorException.class)
    public void create_exception() throws CreatorException {
        testMethod("create() [with exceptions]");

        DepartmentsRepository departmentsRepository = new DepartmentsRepositoryExceptionTestImpl();
        departmentsCreator = new DepartmentsCreatorDefaultImpl(departmentsRepository);
        departmentsCreator.create();
    }

    @AfterClass
    public void teardownClass() {
        testEnd("DepartmentsCreator");
    }
}
