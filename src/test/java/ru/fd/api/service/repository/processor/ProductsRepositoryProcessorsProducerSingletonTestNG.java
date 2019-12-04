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

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import test.producer.entity.ProductProducerTestImpl;
import test.producer.repository.ProductsRepositoryProducerTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class ProductsRepositoryProcessorsProducerSingletonTestNG {

    private ProductsRepositoryProcessors prRepoProcessors;

    @BeforeClass
    public void setUpClass() {
        ProductsRepositoryProducer prRepoProducer = new ProductsRepositoryProducerTestImpl();
        ProductProducer productProducer = new ProductProducerTestImpl();
        prRepoProcessors = new ProductsRepositoryProcessorsSingletonImpl(prRepoProducer, productProducer);
        testBegin("ProductsRepositoryProcessorsSingleton");
    }

    @Test
    public void processor_simple() {
        testMethod("processor() ['simple']");

        ProductsRepositoryProcessor processor = prRepoProcessors.processor("simple");
        assertNotNull(processor, "Simple processor is null!");
        assertTrue(processor instanceof ProductsRepositorySimpleProcessorImpl, "Processor is not instanceof simple!");
        System.out.println("Simple processor: " + processor);
    }

    @Test
    public void processor_attributes() {
        testMethod("processor() ['attributes']");

        ProductsRepositoryProcessor processor = prRepoProcessors.processor("attributes");
        assertNotNull(processor, "Attributes processor is null!");
        assertTrue(processor instanceof ProductsRepositoryWithAttributesProcessorImpl, "Processor is not instanceof attributes!");
        System.out.println("Attributes processor: " + processor);
    }

    @Test
    public void processor_balances() {
        testMethod("processor() ['balances']");

        ProductsRepositoryProcessor processor = prRepoProcessors.processor("balances");
        assertNotNull(processor, "Balances processor is null!");
        assertTrue(processor instanceof ProductsRepositoryWithBalancesProcessorImpl, "Processor is not instanceof balances!");
        System.out.println("Balances processor: " + processor);
    }

    @Test
    public void processor_prices() {
        testMethod("processor() ['prices']");

        ProductsRepositoryProcessor processor = prRepoProcessors.processor("prices");
        assertNotNull(processor, "Prices processor is null!");
        assertTrue(processor instanceof ProductsRepositoryWithPricesProcessorImpl, "Processor is not instanceof prices!");
        System.out.println("Prices processor: " + processor);
    }

    @Test
    public void processor_statuses() {
        testMethod("processor() ['statuses']");

        ProductsRepositoryProcessor processor = prRepoProcessors.processor("statuses");
        assertNotNull(processor, "Statuses processor is null!");
        assertTrue(processor instanceof ProductsRepositoryWithStatusesProcessorImpl, "Processor is not instanceof statuses!");
        System.out.println("Statuses processor: " + processor);
    }

    @Test
    public void processor_shortDescriptions() {
        testMethod("processor() ['shortDescriptions']");

        ProductsRepositoryProcessor processor = prRepoProcessors.processor("shortDescriptions");
        assertNotNull(processor, "ShortDescriptions processor is null!");
        assertTrue(processor instanceof ProductsRepositoryWithShortDescriptionProcessorImpl, "Processor is not instanceof shortDescriptions!");
        System.out.println("ShortDescriptions processor: " + processor);
    }

    @Test
    public void processor_fullDescriptions() {
        testMethod("processor() ['fullDescriptions']");

        ProductsRepositoryProcessor processor = prRepoProcessors.processor("fullDescriptions");
        assertNotNull(processor, "FullDescriptions processor is null!");
        assertTrue(processor instanceof ProductsRepositoryWithFullDescriptionProcessorImpl, "Processor is not instanceof fullDescriptions!");
        System.out.println("FullDescriptions processor: " + processor);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("ProductsRepositoryProcessorsSingleton");
    }
}
