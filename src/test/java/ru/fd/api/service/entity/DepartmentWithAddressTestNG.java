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

public class DepartmentWithAddressTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Department department;

    private String departmentId;
    private String name;
    private String address;

    @BeforeClass
    @Parameters({"departmentId", "name", "address"})
    public void setUpClass(String departmentId, String name, String address) {
        assertNotNull(departmentId, "Department ID cannot be null!");
        assertFalse(departmentId.isEmpty(), "Department ID is empty!");

        assertNotNull(name, "Name cannot be null!");
        assertFalse(name.isEmpty(), "Name is empty!");

        assertNotNull(address, "Address cannot be null!");
        assertFalse(address.isEmpty(), "Address is empty!");

        department = new DepartmentWithAddressImpl(
                new DepartmentImpl(departmentId, name),
                address);
        assertNotNull(department, "Department is null!");

        this.departmentId = departmentId;
        this.name = name;
        this.address = address;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("DepartmentDefault", "formForSend()");

        DepartmentPojo departmentPojo = (DepartmentPojo) department.formForSend();
        assertNotNull(departmentPojo, "DepartmentPojo is null!");
        assertEquals(departmentPojo.getId(), departmentId);
        assertEquals(departmentPojo.getName(), name);
        assertEquals(departmentPojo.getAddress(), address);
        System.out.println(mapper.writeValueAsString(departmentPojo));

        testEnd("DepartmentDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
