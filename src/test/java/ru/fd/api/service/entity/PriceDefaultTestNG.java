package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.PricePojo;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class PriceDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Price price;

    @BeforeClass
    @Parameters({"departmentId", "value"})
    public void setUpClass(String departmentId, double value) {
        assertNotNull(departmentId, "Department ID cannot be null!");
        assertFalse(departmentId.isEmpty(), "Department ID is empty!");
        assertFalse(value < 0, "Value is less than 0!");

        price = new PriceDefaultImpl(departmentId, value);
        assertNotNull(price, "Price is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("PriceDefault", "formForSend()");

        PricePojo pricePojo = (PricePojo) price.formForSend();
        assertNotNull(pricePojo, "PricePojo is null!");
        System.out.println(mapper.writeValueAsString(pricePojo));

        testEnd("PriceDefault", "formForSend()");
    }
}
