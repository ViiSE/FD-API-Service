package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductAttributePojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class ProductAttributeTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Attribute attribute;

    private String attributeId;
    private String value;

    @BeforeClass
    @Parameters({"attributeId", "value"})
    public void setUpClass(String attributeId, String value) {
        assertNotNull(attributeId, "Attribute ID cannot be null!");
        assertFalse(attributeId.isEmpty(), "Attribute ID is empty!");
        assertNotNull(value, "Value cannot be null!");
        assertFalse(value.isEmpty(), "Value is empty!");

        attribute = new ProductAttributeImpl(attributeId, value);
        assertNotNull(attribute, "Attribute is null!");

        this.attributeId = attributeId;
        this.value = value;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(ProductAttributeImpl.class, "formForSend()");

        ProductAttributePojo attributePojo = (ProductAttributePojo) attribute.formForSend();
        assertNotNull(attributePojo, "AttributePojo is null!");
        assertEquals(attributePojo.getAttributeId(), attributeId);
        assertEquals(attributePojo.getValue(), value);
        System.out.println(mapper.writeValueAsString(attributePojo));

        testEnd(ProductAttributeImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
