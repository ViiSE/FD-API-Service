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

package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductPojo;
import ru.fd.api.service.process.test.PsProductTestImpl;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class ProductWithFullDescriptionTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    @BeforeClass
    public void setUpClass() {
        product = new PsProductTestImpl().answer(null);
        assertNotNull(product, "Product is null!");
        testBegin("ProductWithFullDescription", "formForSend()");
    }

    @Test
    public void formForSend_fullDescription() throws JsonProcessingException {
        String fullDescription = "Full description";
        product = new ProductWithFullDescriptionImpl(product, fullDescription);
        assertNotNull(product, "Product with full description is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertNotNull(productPojo.getFullDescription(), "Full description is null!");
        assertEquals(productPojo.getFullDescription(), fullDescription);

        String sendForForm = mapper.writeValueAsString(productPojo);
        assertTrue(sendForForm.contains("\"full_description\":\"" + fullDescription + "\""));
        System.out.println(sendForForm);
    }

    @Test
    public void formForSend_fullDescriptionIsEmpty() throws JsonProcessingException {
        product = new ProductWithFullDescriptionImpl(product, "");
        assertNotNull(product, "Product with full description is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertTrue(productPojo.getFullDescription().isEmpty());

        String sendForForm = mapper.writeValueAsString(productPojo);
        assertTrue(sendForForm.contains("\"full_description\":\"\""));
        System.out.println(sendForForm);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("ProductWithFullDescription", "formForSend()");
    }
}
