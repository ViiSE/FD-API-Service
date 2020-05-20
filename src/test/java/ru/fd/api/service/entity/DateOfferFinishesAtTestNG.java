package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.data.DateOfferPojo;
import test.util.TestUtils;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class DateOfferFinishesAtTestNG {

    private final ObjectMapper mapper = TestUtils.objectMapperWithJavaTimeModule();
    private DateOffer dateOffer;

    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeFinish;

    @BeforeClass
    public void setUpClass() {
        dateTimeStart = LocalDateTime.now();
        dateTimeFinish = LocalDateTime.now().plusDays(30);
        dateOffer = new DateOfferFinishesAtImpl(
                new DateOfferStartsAtImpl(dateTimeStart),
                dateTimeFinish);
        assertNotNull(dateOffer, "Date offer is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(DateOfferFinishesAtImpl.class, "formForSend()");

        DateOfferPojo dateOfferPojo = (DateOfferPojo) dateOffer.formForSend();
        assertNotNull(dateOfferPojo, "DateOfferPojo is null!");
        assertEquals(dateOfferPojo.getStartsAt(), dateTimeStart);
        assertEquals(dateOfferPojo.getFinishesAt(), dateTimeFinish);
        System.out.println(mapper.writeValueAsString(dateOfferPojo));

        testEnd(DateOfferFinishesAtImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
