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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductPojo;
import ru.fd.api.service.process.test.PsProductTestImpl;

import java.util.ArrayList;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class ProductWithBalancesTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    @BeforeClass
    public void setUpClass() {
        product = new PsProductTestImpl().answer(null);
        assertNotNull(product, "Product is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("ProductWithBalances", "formForSend()");

        Balances balances = new BalancesImpl(new ArrayList<>() {{
            add(new BalanceImpl("dep_1", 10));
            add(new BalanceImpl("dep_2", 20)); }});
        product = new ProductWithBalancesImpl(product, balances);
        assertNotNull(product, "Product with balances is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertNotNull(productPojo.getBalances(), "Balances is null!");
        assertTrue(mapper.writeValueAsString(balances.formForSend()).contains(mapper.writeValueAsString(productPojo.getBalances())));

        System.out.println(mapper.writeValueAsString(productPojo));

        testEnd("ProductWithBalances", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
