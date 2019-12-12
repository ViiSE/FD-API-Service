package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.StatusPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class StatusDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Status status;

    private String statusId;
    private String name;

    @BeforeClass
    @Parameters({"statusId", "name"})
    public void setUpClass(String statusId, String name) {
        assertNotNull(statusId, "Status ID cannot be null!");
        assertFalse(statusId.isEmpty(), "Status ID is empty!");
        assertNotNull(name, "Status name cannot be null!");
        assertFalse(name.isEmpty(), "Status name is empty!");

        status = new StatusDefaultImpl(statusId, name);
        assertNotNull(status, "Status is null!");

        this.statusId = statusId;
        this.name = name;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("StatusDefault", "formForSend()");

        StatusPojo statusPojo = (StatusPojo) status.formForSend();
        assertNotNull(statusPojo, "StatusPojo is null!");
        assertEquals(statusPojo.getId(), statusId);
        assertEquals(statusPojo.getName(), name);
        System.out.println(mapper.writeValueAsString(statusPojo));

        testEnd("StatusDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
