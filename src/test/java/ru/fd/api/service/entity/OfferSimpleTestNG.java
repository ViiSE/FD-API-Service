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

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class OfferSimpleTestNG {

    private final ObjectMapper mapper = TestUtils.objectMapperWithJavaTimeModule();
    private Offer offer;

    private long offerId;

    @BeforeClass
    @Parameters({"offerId"})
    public void setUpClass(long offerId) {
        assertFalse(offerId < 0L, "Offer id is less than 0!");
        offer = new OfferSimpleImpl(offerId);
        assertNotNull(offer, "Offer is null!");

        this.offerId = offerId;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(OfferSimpleImpl.class, "formForSend()");

        OfferPojo offerPojo = (OfferPojo) offer.formForSend();
        assertNotNull(offerPojo, "OfferPojo is null!");
        assertEquals(offerPojo.getId(), offerId);
        System.out.println(mapper.writeValueAsString(offerPojo));

        testEnd(OfferSimpleImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
