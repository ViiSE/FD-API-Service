package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.AttributePojo;
import ru.fd.api.service.data.BalancePojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class AttributeDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Attribute attribute;

    private String attributeId;
    private String groupId;
    private String name;

    @BeforeClass
    @Parameters({"attributeId", "groupId", "name"})
    public void setUpClass(String attributeId, String groupId, String name) {
        assertNotNull(attributeId, "Attribute ID cannot be null!");
        assertFalse(attributeId.isEmpty(), "Attribute ID is empty!");

        assertNotNull(groupId, "Group ID cannot be null!");
        assertFalse(groupId.isEmpty(), "Group ID is empty!");

        assertNotNull(name, "Name cannot be null!");
        assertFalse(name.isEmpty(), "Name is empty!");

        attribute = new AttributeDefaultImpl(attributeId, groupId, name);
        assertNotNull(attribute, "Attribute is null!");

        this.attributeId = attributeId;
        this.groupId = groupId;
        this.name = name;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("AttributeDefault", "formForSend()");

        AttributePojo attributePojo = (AttributePojo) attribute.formForSend();
        assertNotNull(attributePojo, "AttributePojo is null!");
        assertEquals(attributePojo.getId(), attributeId);
        assertEquals(attributePojo.getGroupId(), groupId);
        assertEquals(attributePojo.getName(), name);
        System.out.println(mapper.writeValueAsString(attributePojo));

        testEnd("AttributeDefault", "formForSend()");
    }
}
