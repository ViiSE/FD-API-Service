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
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.repository.AttributesRepository;
import ru.fd.api.service.repository.CategoriesRepository;
import test.repository.AttributesRepositoryExceptionTestImpl;
import test.repository.AttributesRepositoryTestImpl;
import test.repository.CategoriesRepositoryExceptionTestImpl;
import test.repository.CategoriesRepositoryTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class CategoriesCreatorTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private CategoriesCreator categoriesCreator;

    @BeforeClass
    public void setUpClass() {
        CategoriesRepository categoriesRepository = new CategoriesRepositoryTestImpl();
        categoriesCreator = new CategoriesCreatorDefaultImpl(categoriesRepository);
        testBegin("CategoriesCreator");
    }

    @Test(priority = 1)
    public void create() throws CreatorException, JsonProcessingException {
        testMethod("create()");

        Categories categories = categoriesCreator.create();
        assertNotNull(categories, "Categories is null!");
        assertTrue(mapper.writeValueAsString(categories.formForSend()).contains("\"categories\""));
        System.out.println(mapper.writeValueAsString(categories.formForSend()));
    }

    @Test(priority = 2, expectedExceptions = CreatorException.class)
    public void create_exception() throws CreatorException {
        testMethod("create() [with exceptions]");

        CategoriesRepository categoriesRepository = new CategoriesRepositoryExceptionTestImpl();
        categoriesCreator = new CategoriesCreatorDefaultImpl(categoriesRepository);
        categoriesCreator.create();
    }

    @AfterClass
    public void teardownClass() {
        testEnd("CategoriesCreator");
    }
}
