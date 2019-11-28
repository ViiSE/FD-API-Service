package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.StatusPojo;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class StatusDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Status status;

    @BeforeClass
    @Parameters({"departmentId", "statusId"})
    public void setUpClass(String departmentId, String statusId) {
        assertNotNull(departmentId, "Department ID cannot be null!");
        assertFalse(departmentId.isEmpty(), "Department ID is empty!");

        assertNotNull(statusId, "Status ID cannot be null!");
        assertFalse(statusId.isEmpty(), "Status ID is empty!");

        status = new StatusDefaultImpl(departmentId, statusId);
        assertNotNull(status, "Status is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("StatusDefault", "formForSend()");

        StatusPojo statusPojo = (StatusPojo) status.formForSend();
        assertNotNull(statusPojo, "StatusPojo is null!");
        System.out.println(mapper.writeValueAsString(statusPojo));

        testEnd("StatusDefault", "formForSend()");
    }
}
