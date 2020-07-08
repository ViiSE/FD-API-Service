package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.PriceOfferPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class PriceOfferWithDepartmentIdTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Price price;

    private float origValue;
    private float offerValue;
    private String depId;

    @BeforeClass
    @Parameters({"origValue", "offerValue", "depId"})
    public void setUpClass(float origValue, float offerValue, String depId) {
        assertNotNull(depId, "Department ID cannot be null!");
        assertFalse(depId.isEmpty(), "Department ID is empty!");
        assertFalse(origValue < 0.f, "Original value is less than 0!");
        assertFalse(offerValue < 0.f, "Offer value is less than 0!");

        price = new PriceOfferWithDepartmentIdImpl(
                new PriceOfferImpl(origValue, offerValue),
                depId);
        assertNotNull(price, "Price is null!");

        this.depId = depId;
        this.origValue = origValue;
        this.offerValue = offerValue;
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(PriceOfferWithDepartmentIdImpl.class, "formForSend()");

        PriceOfferPojo priceOfferPojo = (PriceOfferPojo) price.formForSend();
        assertNotNull(priceOfferPojo, "PriceOfferPojo is null!");
        assertEquals(priceOfferPojo.getOriginalValue(), origValue);
        assertEquals(priceOfferPojo.getOfferValue(), offerValue);
        System.out.println(mapper.writeValueAsString(priceOfferPojo));

        testEnd(PriceOfferWithDepartmentIdImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
