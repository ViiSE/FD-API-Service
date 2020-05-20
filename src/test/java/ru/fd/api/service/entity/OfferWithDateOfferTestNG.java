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

import java.time.LocalDateTime;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class OfferWithDateOfferTestNG {

    private final ObjectMapper mapper = TestUtils.objectMapperWithJavaTimeModule();
    private Offer offer;

    private long offerId;
    private LocalDateTime startsAt;
    private LocalDateTime finishesAt;

    @BeforeClass
    @Parameters({"offerId"})
    public void setUpClass(long offerId) {
        assertFalse(offerId < 0L, "Offer id is less than 0!");

        LocalDateTime startsAt = LocalDateTime.now();
        LocalDateTime finishesAt = LocalDateTime.now().plusDays(30);

        offer = new OfferWithDateOfferImpl(
                new OfferSimpleImpl(offerId),
                new DateOfferFinishesAtImpl(
                        new DateOfferStartsAtImpl(startsAt),
                        finishesAt));
        assertNotNull(offer, "Offer is null!");

        this.offerId = offerId;
        this.startsAt = startsAt;
        this.finishesAt = finishesAt;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(OfferWithDateOfferImpl.class, "formForSend()");

        OfferPojo offerPojo = (OfferPojo) offer.formForSend();
        assertNotNull(offerPojo, "OfferPojo is null!");
        assertEquals(offerPojo.getId(), offerId);
        assertEquals(offerPojo.getDateOffer().getStartsAt(), startsAt);
        assertEquals(offerPojo.getDateOffer().getFinishesAt(), finishesAt);
        System.out.println(mapper.writeValueAsString(offerPojo));

        testEnd(OfferWithDateOfferImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
