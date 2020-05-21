package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.AttributeGroupsPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class AttributeGroupsTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private AttributeGroups attributeGroups;

    @BeforeClass
    @Parameters({"attributeGroupId1", "name1", "attributeGroupId2", "name2"})
    public void setUpClass(long attributeGroupsId1, String name1, long attributeGroupsId2, String name2) {
        assertFalse(attributeGroupsId1 < 0L, "Attribute group ID is less than 0!");
        assertNotNull(name1, "Attribute group name cannot be null!");
        assertFalse(name1.isEmpty(), "Attribute group name is empty!");

        assertFalse(attributeGroupsId2 < 0L, "Attribute group ID is less than 0!");
        assertNotNull(name2, "Attribute group name cannot be null!");
        assertFalse(name2.isEmpty(), "Attribute group name is empty!");

        AttributeGroup attributeGroup1 = new AttributeGroupImpl(attributeGroupsId1, name1);
        AttributeGroup attributeGroup2 = new AttributeGroupImpl(attributeGroupsId2, name2);

        attributeGroups = new AttributeGroupsImpl(new ArrayList<>() {{ add(attributeGroup1); add(attributeGroup2); }});
        assertNotNull(attributeGroups, "AttributeGroups is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("AttributeGroupsDefault", "formForSend()");

        AttributeGroupsPojo attributeGroupsPojo = (AttributeGroupsPojo) attributeGroups.formForSend();
        assertNotNull(attributeGroupsPojo, "AttributeGroupsPojo is null!");
        System.out.println(mapper.writeValueAsString(attributeGroupsPojo));

        testEnd("AttributeGroupsDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
