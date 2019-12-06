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
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessors;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessorsSingletonImpl;
import test.producer.entity.ProductProducerTestImpl;
import test.producer.repository.ProductsRepositoryProducerExceptionTestImpl;
import test.producer.repository.ProductsRepositoryProducerTestImpl;
import test.repository.processor.ProductsRepositoryProcessorsNotSingletonImpl;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class ProductsCreatorTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private ProductsCreator productsCreator;
    private List<String> with;

    @BeforeClass
    public void setUpClass() {
        with = new ArrayList<>();
        ProductsRepositoryProducer productsRepoProducer = new ProductsRepositoryProducerTestImpl();
        ProductProducer productProducer = new ProductProducerTestImpl();
        ProductsRepositoryProcessors repoProcessors =
                new ProductsRepositoryProcessorsSingletonImpl(productsRepoProducer, productProducer);
        productsCreator = new ProductsCreatorDefaultImpl(repoProcessors, with);
        testBegin("ProductsCreator");
    }

    @Test(priority = 1)
    public void create() throws CreatorException, JsonProcessingException {
        testMethod("create()");

        Products products = productsCreator.create();
        assertNotNull(products, "Products is null!");
        assertTrue(mapper.writeValueAsString(products.formForSend()).contains("\"products\""));
        System.out.println(mapper.writeValueAsString(products.formForSend()));
    }

    @Test(priority = 2)
    public void create_with() throws CreatorException, JsonProcessingException {
        testMethod("create() [with]");

        with.add("attributes");
        with.add("statuses");
        ProductsRepositoryProducer productsRepoProducer = new ProductsRepositoryProducerTestImpl();
        ProductProducer productProducer = new ProductProducerTestImpl();
        ProductsRepositoryProcessors repoProcessors =
                new ProductsRepositoryProcessorsSingletonImpl(productsRepoProducer, productProducer);
        productsCreator = new ProductsCreatorDefaultImpl(repoProcessors, with);

        Products products = productsCreator.create();
        assertNotNull(products, "Products is null!");
        assertTrue(mapper.writeValueAsString(products.formForSend()).contains("\"products\""));
        assertTrue(mapper.writeValueAsString(products.formForSend()).contains("\"attributes\""));
        assertTrue(mapper.writeValueAsString(products.formForSend()).contains("\"statuses\""));
        System.out.println(mapper.writeValueAsString(products.formForSend()));
    }

    @Test(priority = 3, expectedExceptions = CreatorException.class)
    public void create_exception() throws CreatorException {
        testMethod("create() [with exceptions]");

        with.clear();
        with.add("attributes");
        ProductsRepositoryProducer productsRepoProducer = new ProductsRepositoryProducerExceptionTestImpl();
        ProductProducer productProducer = new ProductProducerTestImpl();
        ProductsRepositoryProcessors repoProcessors =
                new ProductsRepositoryProcessorsNotSingletonImpl(productsRepoProducer, productProducer);
        ProductsCreator productsCreator = new ProductsCreatorDefaultImpl(repoProcessors, with);
        productsCreator.create();
    }

    @AfterClass
    public void teardownClass() {
        testEnd("ProductsCreator");
    }
}
