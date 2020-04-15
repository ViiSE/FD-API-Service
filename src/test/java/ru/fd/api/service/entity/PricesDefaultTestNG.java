package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.PricesPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class PricesDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Prices prices;

    @BeforeClass
    @Parameters({"departmentId1", "value1", "departmentId2", "value2"})
    public void setUpClass(String departmentId1, float value1, String departmentId2, float value2) {
        assertNotNull(departmentId1, "Department ID cannot be null!");
        assertFalse(departmentId1.isEmpty(), "Department ID is empty!");
        assertFalse(value1 < 0f, "Value is less than 0!");

        assertNotNull(departmentId2, "Department ID cannot be null!");
        assertFalse(departmentId2.isEmpty(), "Department ID is empty!");
        assertFalse(value2 < 0f, "Value is less than 0!");

        Price price1 = new PriceImpl(departmentId1, value1);
        Price price2 = new PriceImpl(departmentId2, value2);

        prices = new PricesImpl(new ArrayList<>() {{ add(price1); add(price2); }});
        assertNotNull(prices, "Prices is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("PricesDefault", "formForSend()");

        PricesPojo pricesPojo = (PricesPojo) prices.formForSend();
        assertNotNull(pricesPojo, "PricesPojo is null!");
        System.out.println(mapper.writeValueAsString(pricesPojo));

        testEnd("PricesDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
