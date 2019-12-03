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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.exception.RepositoryException;
import test.repository.CategoriesRepositoryTestImpl;
import test.repository.DepartmentsRepositoryTestImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class DepartmentsRepositoryTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private DepartmentsRepository departmentsRepository;

    @BeforeClass
    public void setUpClass() {
        departmentsRepository = new DepartmentsRepositoryTestImpl();
    }

    @Test
    public void readProducts() throws JsonProcessingException {
        testBegin("DepartmentsRepository", "readDepartments()");

        try {
            Departments departments = departmentsRepository.readDepartments();
            System.out.println("Departments:");
            String pojo = mapper.writeValueAsString(departments.formForSend());
            assertTrue(pojo.contains("\"departments\":"));
            System.out.println(pojo);

        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("DepartmentsRepository", "readDepartments()");
    }
}
