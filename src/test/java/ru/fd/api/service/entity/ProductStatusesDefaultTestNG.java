package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductStatusesPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class ProductStatusesDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Statuses statuses;

    @BeforeClass
    @Parameters({"departmentId1", "statusId1", "departmentId2", "statusId2"})
    public void setUpClass(String departmentId1, String statusId1, String departmentId2, String statusId2) {
        assertNotNull(departmentId1, "Department ID cannot be null!");
        assertFalse(departmentId1.isEmpty(), "Department ID is empty!");
        assertNotNull(statusId1, "Status ID cannot be null!");
        assertFalse(statusId1.isEmpty(), "Status ID is empty!");

        assertNotNull(departmentId2, "Department ID cannot be null!");
        assertFalse(departmentId2.isEmpty(), "Department ID is empty!");
        assertNotNull(statusId2, "Status ID cannot be null!");
        assertFalse(statusId2.isEmpty(), "Status ID is empty!");

        Status status1 = new ProductStatusImpl(departmentId1, statusId1);
        Status status2 = new ProductStatusImpl(departmentId2, statusId2);

        statuses = new ProductStatusesImpl(new ArrayList<>(){{ add(status1); add(status2); }});
        assertNotNull(statuses, "Statuses is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("ProductStatusesDefault", "formForSend()");

        ProductStatusesPojo statusesPojo = (ProductStatusesPojo) statuses.formForSend();
        assertNotNull(statusesPojo, "StatusesPojo is null!");
        System.out.println(mapper.writeValueAsString(statusesPojo));

        testEnd("ProductStatusesDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
