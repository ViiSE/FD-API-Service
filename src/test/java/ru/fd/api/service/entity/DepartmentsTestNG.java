package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.DepartmentsPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class DepartmentsTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Departments departments;

    @BeforeClass
    @Parameters({"departmentId1", "name1", "departmentId2", "name2"})
    public void setUpClass(String departmentId1, String name1, String departmentId2, String name2) {
        assertNotNull(departmentId1, "Department ID cannot be null!");
        assertFalse(departmentId1.isEmpty(), "Department ID is empty!");
        assertNotNull(name1, "Department name cannot be null!");
        assertFalse(name1.isEmpty(), "Department name is empty!");

        assertNotNull(departmentId2, "Department ID cannot be null!");
        assertFalse(departmentId2.isEmpty(), "Department ID is empty!");
        assertNotNull(name2, "Department name cannot be null!");
        assertFalse(name2.isEmpty(), "Department name is empty!");

        Department department1 = new DepartmentImpl(departmentId1, name1);
        Department department2 = new DepartmentImpl(departmentId2, name2);

        departments = new DepartmentsImpl(new ArrayList<>() {{ add(department1); add(department2); }});
        assertNotNull(departments, "Departments is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("DepartmentsDefault", "formForSend()");

        DepartmentsPojo departmentsPojo = (DepartmentsPojo) departments.formForSend();
        assertNotNull(departmentsPojo, "DepartmentsPojo is null!");
        System.out.println(mapper.writeValueAsString(departmentsPojo));

        testEnd("DepartmentsDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
