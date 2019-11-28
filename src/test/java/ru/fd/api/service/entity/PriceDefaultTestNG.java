package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.PricePojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class PriceDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Price price;

    private String departmentId;
    private float value;

    @BeforeClass
    @Parameters({"departmentId", "value"})
    public void setUpClass(String departmentId, float value) {
        assertNotNull(departmentId, "Department ID cannot be null!");
        assertFalse(departmentId.isEmpty(), "Department ID is empty!");
        assertFalse(value < 0f, "Value is less than 0!");

        price = new PriceDefaultImpl(departmentId, value);
        assertNotNull(price, "Price is null!");

        this.departmentId = departmentId;
        this.value = value;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("PriceDefault", "formForSend()");

        PricePojo pricePojo = (PricePojo) price.formForSend();
        assertNotNull(pricePojo, "PricePojo is null!");
        assertEquals(pricePojo.getDepartmentId(), departmentId);
        assertEquals(pricePojo.getValue(), value);
        System.out.println(mapper.writeValueAsString(pricePojo));

        testEnd("PriceDefault", "formForSend()");
    }
}
