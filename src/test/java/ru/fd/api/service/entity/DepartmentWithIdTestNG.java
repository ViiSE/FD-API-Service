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

public class DepartmentWithIdTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Department department;

    private String departmentId;

    @BeforeClass
    @Parameters({"departmentId"})
    public void setUpClass(String departmentId) {
        assertNotNull(departmentId, "Department ID cannot be null!");
        assertFalse(departmentId.isEmpty(), "Department ID is empty!");

        department = new DepartmentWithIdImpl(departmentId);
        assertNotNull(department, "Department is null!");

        this.departmentId = departmentId;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(DepartmentWithIdImpl.class, "formForSend()");

        DepartmentPojo departmentPojo = (DepartmentPojo) department.formForSend();
        assertNotNull(departmentPojo, "DepartmentPojo is null!");
        assertEquals(departmentPojo.getId(), departmentId);
        System.out.println(mapper.writeValueAsString(departmentPojo));

        testEnd(DepartmentWithIdImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
