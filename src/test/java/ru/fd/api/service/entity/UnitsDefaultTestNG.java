package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.UnitsPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class UnitsDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Units units;

    @BeforeClass
    @Parameters({"unitId1", "name1", "unitId2", "name2"})
    public void setUpClass(String unitId1, String name1, String unitId2, String name2) {
        assertNotNull(unitId1, "Unit ID cannot be null!");
        assertFalse(unitId1.isEmpty(), "Unit ID is empty!");
        assertNotNull(name1, "Unit name cannot be null!");
        assertFalse(name1.isEmpty(), "Unit name is empty!");

        assertNotNull(unitId2, "Unit ID cannot be null!");
        assertFalse(unitId2.isEmpty(), "Unit ID is empty!");
        assertNotNull(name2, "Unit name cannot be null!");
        assertFalse(name2.isEmpty(), "Unit name is empty!");

        Unit unit1 = new UnitImpl(unitId1, name1);
        Unit unit2 = new UnitImpl(unitId2, name2);

        units = new UnitsImpl(new ArrayList<>() {{ add(unit1); add(unit2); }});
        assertNotNull(units, "Units is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("UnitsDefault", "formForSend()");

        UnitsPojo unitsPojo = (UnitsPojo) units.formForSend();
        assertNotNull(unitsPojo, "UnitsPojo is null!");
        System.out.println(mapper.writeValueAsString(unitsPojo));

        testEnd("UnitsDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
