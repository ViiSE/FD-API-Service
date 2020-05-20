package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.OfferPojo;
import test.util.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class OfferWithDepartmentsTestNG {

    private final ObjectMapper mapper = TestUtils.objectMapperWithJavaTimeModule();
    private Offer offer;

    private long offerId;

    @BeforeClass
    @Parameters({"offerId", "depId1", "depId2"})
    public void setUpClass(long offerId, String depId1, String depId2) {
        assertFalse(offerId < 0L, "Offer id is less than 0!");
        assertNotNull(depId1, "Department ID is null!");
        assertNotNull(depId2, "Department ID is null!");
        assertFalse(depId1.isEmpty(), "Department ID is empty!");
        assertFalse(depId2.isEmpty(), "Department ID is empty!");

        List<Department> departments = new ArrayList<>() {{
            add(new DepartmentWithIdImpl(depId1));
            add(new DepartmentWithIdImpl(depId2));
        }};

        offer = new OfferWithDepartmentsImpl(
                new OfferSimpleImpl(offerId),
                departments);

        assertNotNull(offer, "Offer is null!");

        this.offerId = offerId;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(OfferWithDepartmentsImpl.class, "formForSend()");

        OfferPojo offerPojo = (OfferPojo) offer.formForSend();
        assertNotNull(offerPojo, "OfferPojo is null!");
        assertEquals(offerPojo.getId(), offerId);
        System.out.println(mapper.writeValueAsString(offerPojo));

        testEnd(OfferWithDepartmentsImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
