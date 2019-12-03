package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductAttributesPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class ProductAttributesDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Attributes attributes;

    @BeforeClass
    @Parameters({"attributeId1", "value1", "attributeId2", "value2"})
    public void setUpClass(String attributeId1, String value1, String attributeId2, String value2) {
        assertNotNull(attributeId1, "Attribute ID cannot be null!");
        assertFalse(attributeId1.isEmpty(), "Attribute ID is empty!");
        assertNotNull(value1, "Value cannot be null!");
        assertFalse(value1.isEmpty(), "Value is empty!");

        assertNotNull(attributeId2, "Attribute ID cannot be null!");
        assertFalse(attributeId2.isEmpty(), "Attribute ID is empty!");
        assertNotNull(value2, "Value cannot be null!");
        assertFalse(value2.isEmpty(), "Value is empty!");

        ProductAttribute attribute1 = new ProductAttributeDefaultImpl(attributeId1, value1);
        ProductAttribute attribute2 = new ProductAttributeDefaultImpl(attributeId2, value2);

        attributes = new ProductAttributesDefaultImpl(new ArrayList<>() {{ add(attribute1); add(attribute2); }});
        assertNotNull(attributes, "Attributes is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("ProductAttributesDefault", "formForSend()");

        ProductAttributesPojo attributesPojo = (ProductAttributesPojo) attributes.formForSend();
        assertNotNull(attributesPojo, "ProductAttributesPojo is null!");
        System.out.println(mapper.writeValueAsString(attributesPojo));

        testEnd("ProductAttributesDefault", "formForSend()");
    }
}
