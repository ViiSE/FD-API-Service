package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.StatusesPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class StatusesDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Statuses statuses;

    @BeforeClass
    @Parameters({"statusId1", "name1", "statusId2", "name2"})
    public void setUpClass(String statusId1, String name1, String statusId2, String name2) {
        assertNotNull(statusId1, "Status ID cannot be null!");
        assertFalse(statusId1.isEmpty(), "Status ID is empty!");
        assertNotNull(name1, "Status name cannot be null!");
        assertFalse(name1.isEmpty(), "Status name is empty!");

        assertNotNull(statusId2, "Status ID cannot be null!");
        assertFalse(statusId2.isEmpty(), "Status ID is empty!");
        assertNotNull(name2, "Status name cannot be null!");
        assertFalse(name2.isEmpty(), "Status name is empty!");

        Status status1 = new StatusImpl(statusId1, name1);
        Status status2 = new StatusImpl(statusId2, name2);

        statuses = new StatusesImpl(new ArrayList<>() {{ add(status1); add(status2); }});
        assertNotNull(statuses, "Statuses is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("StatusesDefault", "formForSend()");

        StatusesPojo statusesPojo = (StatusesPojo) statuses.formForSend();
        assertNotNull(statusesPojo, "StatusesPojo is null!");
        System.out.println(mapper.writeValueAsString(statusesPojo));

        testEnd("StatusesDefault", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
