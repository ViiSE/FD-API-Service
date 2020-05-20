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

public class OfferWithNameTestNG {

    private final ObjectMapper mapper = TestUtils.objectMapperWithJavaTimeModule();
    private Offer offer;

    private long offerId;
    private String name;

    @BeforeClass
    @Parameters({"offerId", "name"})
    public void setUpClass(long offerId, String name) {
        assertFalse(offerId < 0L, "Offer id is less than 0!");
        assertNotNull(name, "Offer name is null!");
        assertFalse(name.isEmpty(), "Offer name is empty!");
        offer = new OfferWithNameImpl(
                new OfferSimpleImpl(offerId),
                name);
        assertNotNull(offer, "Offer is null!");

        this.offerId = offerId;
        this.name = name;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(OfferWithNameImpl.class, "formForSend()");

        OfferPojo offerPojo = (OfferPojo) offer.formForSend();
        assertNotNull(offerPojo, "OfferPojo is null!");
        assertEquals(offerPojo.getId(), offerId);
        assertEquals(offerPojo.getName(), name);
        System.out.println(mapper.writeValueAsString(offerPojo));

        testEnd(OfferWithNameImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
