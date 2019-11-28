package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.AttributePojo;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class AttributeDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Attribute attribute;

    @BeforeClass
    @Parameters({"attributeId", "value"})
    public void setUpClass(String attributeId, String value) {
        assertNotNull(attributeId, "Attribute ID cannot be null!");
        assertFalse(attributeId.isEmpty(), "Attribute ID is empty!");

        assertNotNull(value, "Value cannot be null!");
        assertFalse(value.isEmpty(), "Value is empty!");

        attribute = new AttributeDefaultImpl(attributeId, value);
        assertNotNull(attribute, "Attribute is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("AttributeDefault", "formForSend()");

        AttributePojo attributePojo = (AttributePojo) attribute.formForSend();
        assertNotNull(attributePojo, "AttributePojo is null!");
        System.out.println(mapper.writeValueAsString(attributePojo));

        testEnd("AttributeDefault", "formForSend()");
    }
}
