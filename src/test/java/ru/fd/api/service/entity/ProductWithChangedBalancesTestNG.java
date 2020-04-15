/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductChangedBalancesPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class ProductWithChangedBalancesTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    @BeforeClass
    @Parameters({"id", "departmentId1", "quantity1", "departmentId2", "quantity2"})
    public void setUpClass(String id, String departmentId1, int quantity1, String departmentId2, int quantity2) {
        assertNotNull(id, "ID cannot be null!");
        assertFalse(id.isEmpty(), "ID is empty!");

        assertNotNull(departmentId1, "Department ID cannot be null!");
        assertFalse(departmentId1.isEmpty(), "Department ID is empty!");
        assertFalse(quantity1 < 0, "Quantity is less than 0!");

        assertNotNull(departmentId2, "Department ID cannot be null!");
        assertFalse(departmentId2.isEmpty(), "Department ID is empty!");
        assertFalse(quantity2 < 0, "Quantity is less than 0!");

        Balance balance1 = new BalanceImpl(departmentId1, quantity1);
        Balance balance2 = new BalanceImpl(departmentId2, quantity2);

        Balances balances = new BalancesImpl(new ArrayList<>() {{ add(balance1); add(balance2); }});
        assertNotNull(balances, "Balances is null!");

        product = new ProductWithChangedBalancesImpl(id, balances);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("ProductWithChangedBalances", "formForSend()");

        ProductChangedBalancesPojo productP = (ProductChangedBalancesPojo) product.formForSend();
        assertNotNull(productP, "ProductChangedBalancesPojo is null!");
        System.out.println(mapper.writeValueAsString(productP));

        testEnd("ProductWithChangedBalances", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
