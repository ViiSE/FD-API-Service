/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import ru.fd.api.service.ApiServiceApplication;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import test.util.TestUtils;

import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

@SpringBootTest(classes = ApiServiceApplication.class)
public class ChangedBalancesWithOrderIdRepositoryProcessorIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("changedBalancesRepositoryWithOrderIdProcessor")
    private ProductsRepositoryProcessor repoProcessor;

    ObjectMapper mapper = TestUtils.objectMapperWithJavaTimeModule();

    @Test
    public void apply() throws RepositoryException, JsonProcessingException {
        testBegin("ChangedBalancesRepositoryWithOrderIdProcessor, apply()");

        Products products = (Products) repoProcessor.apply("nothing");

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products.formForSend()));

        testEnd("ChangedBalancesRepositoryWithOrderIdProcessor, apply()");
    }
}
