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

public class DateOfferStartsAtTestNG {

    private final ObjectMapper mapper = TestUtils.objectMapperWithJavaTimeModule();
    private DateOffer dateOffer;

    private LocalDateTime dateTime;

    @BeforeClass
    public void setUpClass() {
        dateTime = LocalDateTime.now();
        dateOffer = new DateOfferStartsAtImpl(dateTime);
        assertNotNull(dateOffer, "Date offer is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(DateOfferStartsAtImpl.class, "formForSend()");

        DateOfferPojo dateOfferPojo = (DateOfferPojo) dateOffer.formForSend();
        assertNotNull(dateOfferPojo, "DateOfferPojo is null!");
        assertEquals(dateOfferPojo.getStartsAt(), dateTime);
        System.out.println(mapper.writeValueAsString(dateOfferPojo));

        testEnd(DateOfferStartsAtImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
