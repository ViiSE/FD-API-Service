package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.PricesOfferPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class PricesOfferTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Prices prices;

    @BeforeClass
    @Parameters({"departmentId1", "origValue1", "offerValue1", "departmentId2", "origValue2", "offerValue2"})
    public void setUpClass(String departmentId1, float origValue1, float offerValue1, String departmentId2, float origValue2, float offerValue2) {
        assertNotNull(departmentId1, "Department ID cannot be null!");
        assertFalse(departmentId1.isEmpty(), "Department ID is empty!");
        assertFalse(origValue1 < 0f, "Original value is less than 0!");
        assertFalse(offerValue1 < 0f, "Offer value is less than 0!");

        assertNotNull(departmentId2, "Department ID cannot be null!");
        assertFalse(origValue2 < 0f, "Original value is less than 0!");
        assertFalse(offerValue2 < 0f, "Offer value is less than 0!");

        Price price1 = new PriceOfferWithDepartmentIdImpl(
                new PriceOfferImpl(
                        origValue1,
                        offerValue1),
                departmentId1);
        Price price2 = new PriceOfferWithDepartmentIdImpl(
                new PriceOfferImpl(
                        origValue2,
                        offerValue2),
                departmentId2);

        prices = new PricesOfferImpl(new ArrayList<>() {{ add(price1); add(price2); }});
        assertNotNull(prices, "Prices offer is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin(PricesOfferImpl.class, "formForSend()");

        PricesOfferPojo pricesOfferPojo = (PricesOfferPojo) prices.formForSend();
        assertNotNull(pricesOfferPojo, "PricesOfferPojo is null!");
        System.out.println(mapper.writeValueAsString(pricesOfferPojo));

        testEnd(PricesOfferImpl.class, "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
