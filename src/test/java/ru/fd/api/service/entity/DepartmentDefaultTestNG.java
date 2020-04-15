package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.DepartmentPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class DepartmentDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Department department;

    private String departmentId;
    private String name;

    @BeforeClass
    @Parameters({"departmentId", "name"})
    public void setUpClass(String departmentId, String name) {
        assertNotNull(departmentId, "Department ID cannot be null!");
        assertFalse(departmentId.isEmpty(), "Department ID is empty!");

        assertNotNull(name, "Name cannot be null!");
        assertFalse(name.isEmpty(), "Name is empty!");

        department = new DepartmentImpl(departmentId, name);
        assertNotNull(department, "Department is null!");

        this.departmentId = departmentId;
        this.name = name;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("DepartmentDefault", "formForSend()");

        DepartmentPojo departmentPojo = (DepartmentPojo) department.formForSend();
        assertNotNull(departmentPojo, "DepartmentPojo is null!");
        assertEquals(departmentPojo.getId(), departmentId);
        assertEquals(departmentPojo.getName(), name);
        System.out.println(mapper.writeValueAsString(departmentPojo));

        testEnd("DepartmentDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
