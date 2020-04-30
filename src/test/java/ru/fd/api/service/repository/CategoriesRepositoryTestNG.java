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
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.test.CategoriesRepositoryTestImpl;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class CategoriesRepositoryTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private CategoriesRepository categoriesRepository;

    @BeforeClass
    public void setUpClass() {
        categoriesRepository = new CategoriesRepositoryTestImpl();
    }

    @Test
    public void readProducts() throws JsonProcessingException {
        testBegin("CategoriesRepository", "read()");

        try {
            Categories categories = categoriesRepository.read();
            System.out.println("Categories:");
            String pojo = mapper.writeValueAsString(categories.formForSend());
            assertTrue(pojo.contains("\"categories\":"));
            System.out.println(pojo);
        } catch (RepositoryException ex) {
            catchMessage(ex);
        }

        testEnd("CategoriesRepository", "read()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
