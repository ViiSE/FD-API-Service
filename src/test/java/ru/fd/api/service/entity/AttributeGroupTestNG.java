package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.AttributeGroupPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class AttributeGroupTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private AttributeGroup attributeGroup;

    private String attributeGroupId;
    private String name;

    @BeforeClass
    @Parameters({"attributeGroupId", "name"})
    public void setUpClass(String attributeGroupId, String name) {
        assertNotNull(attributeGroupId, "Attribute group ID cannot be null!");
        assertFalse(attributeGroupId.isEmpty(), "Attribute group ID is empty!");

        assertNotNull(name, "Name cannot be null!");
        assertFalse(name.isEmpty(), "Name is empty!");

        attributeGroup = new AttributeGroupImpl(attributeGroupId, name);
        assertNotNull(attributeGroup, "Attribute group is null!");

        this.attributeGroupId = attributeGroupId;
        this.name = name;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("AttributeGroupDefault", "formForSend()");

        AttributeGroupPojo attributeGroupPojo = (AttributeGroupPojo) attributeGroup.formForSend();
        assertNotNull(attributeGroupPojo, "AttributeGroupPojo is null!");
        assertEquals(attributeGroupPojo.getId(), attributeGroupId);
        assertEquals(attributeGroupPojo.getName(), name);
        System.out.println(mapper.writeValueAsString(attributeGroupPojo));

        testEnd("AttributeGroupDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
