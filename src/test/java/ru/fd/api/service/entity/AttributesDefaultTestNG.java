package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.AttributesPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class AttributesDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Attributes attributes;

    @BeforeClass
    @Parameters({"attributeId1", "groupId1", "name1", "attributeId2", "groupId2", "name2"})
    public void setUpClass(String attributeId1, String groupId1, String name1,
                           String attributeId2, String groupId2, String name2) {
        assertNotNull(attributeId1, "Attribute ID cannot be null!");
        assertFalse(attributeId1.isEmpty(), "Attribute ID is empty!");
        assertNotNull(groupId1, "Group cannot be null!");
        assertFalse(groupId1.isEmpty(), "Attribute ID is empty!");
        assertNotNull(name1, "Name cannot be null!");
        assertFalse(name1.isEmpty(), "Name ID is empty!");

        assertNotNull(attributeId2, "Attribute ID cannot be null!");
        assertFalse(attributeId2.isEmpty(), "Attribute ID is empty!");
        assertNotNull(groupId2, "Group cannot be null!");
        assertFalse(groupId2.isEmpty(), "Attribute ID is empty!");
        assertNotNull(name2, "Name cannot be null!");
        assertFalse(name2.isEmpty(), "Name ID is empty!");

        Attribute attribute1 = new AttributeImpl(attributeId1, groupId1, name1);
        Attribute attribute2 = new AttributeImpl(attributeId2, groupId2, name2);

        attributes = new AttributesImpl(new ArrayList<>() {{ add(attribute1); add(attribute2); }});
        assertNotNull(attributes, "Attributes is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("AttributesDefault", "formForSend()");

        AttributesPojo attributesPojo = (AttributesPojo) attributes.formForSend();
        assertNotNull(attributesPojo, "AttributesPojo is null!");
        System.out.println(mapper.writeValueAsString(attributesPojo));

        testEnd("AttributesDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
