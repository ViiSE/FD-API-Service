package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.UnitPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class UnitDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Unit unit;

    private String unitId;
    private String name;

    @BeforeClass
    @Parameters({"unitId", "name"})
    public void setUpClass(String unitId, String name) {
        assertNotNull(unitId, "Unit ID cannot be null!");
        assertFalse(unitId.isEmpty(), "Unit ID is empty!");
        assertNotNull(name, "Unit name cannot be null!");
        assertFalse(name.isEmpty(), "Unit name is empty!");

        unit = new UnitDefaultImpl(unitId, name);
        assertNotNull(unit, "Unit is null!");

        this.unitId = unitId;
        this.name = name;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("UnitDefault", "formForSend()");

        UnitPojo unitPojo = (UnitPojo) unit.formForSend();
        assertNotNull(unitPojo, "UnitPojo is null!");
        assertEquals(unitPojo.getId(), unitId);
        assertEquals(unitPojo.getName(), name);
        System.out.println(mapper.writeValueAsString(unitPojo));

        testEnd("UnitDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
