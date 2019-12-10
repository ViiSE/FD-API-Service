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

package ru.fd.api.service.repository.processor;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.ProductsRepository;
import test.producer.entity.ProductProducerTestImpl;
import test.producer.repository.ProductsRepositoryProducerTestImpl;
import test.repository.ProductsRepositorySimpleTestImpl;
import test.repository.ProductsRepositoryWithAttributesTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class ProductsRepositoryWithAttributesProcessorTestNG {

    private ProductsRepositoryProcessor processor;
    private ProductsRepository prRepoSimple;

    @BeforeTest
    public void setUpClass() {
        ProductsRepositoryProducer prRepoProducer = new ProductsRepositoryProducerTestImpl();
        ProductProducer productProducer = new ProductProducerTestImpl();
        processor = new ProductsRepositoryWithAttributesProcessorImpl(prRepoProducer, productProducer,null);
        prRepoSimple = new ProductsRepositorySimpleTestImpl();
    }

    @Test
    public void apply() {
        testBegin("ProductsRepositoryWithAttributesProcessor", "apply()");

        ProductsRepository prRepo = processor.apply(prRepoSimple);
        assertNotNull(prRepo, "ProductsRepository is null!");
        assertTrue(prRepo instanceof ProductsRepositoryWithAttributesTestImpl, "Products repository is not instanceof test attributes!");
        System.out.println("ProductsRepository: " + prRepo);

        testEnd("ProductsRepositoryWithAttributesProcessor", "apply()");
    }
}
