package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.fd.api.service.data.ProductOfferPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class ProductOfferWithOfferPriceTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    private String id;

    @BeforeClass
    @Parameters({"id", "origValue", "offerValue"})
    public void setUpClass(String id, float origValue, float offerValue) {
        assertNotNull(id, "ID cannot be null!");
        assertFalse(id.isEmpty(), "ID is empty!");

        assertFalse(origValue < 0.f, "Original value price is less than 0!");
        assertFalse(offerValue < 0.f, "Offer value price is less than 0!");

        product = new ProductOfferWithOfferPriceImpl(
                new ProductOfferWithIdImpl(id),
                new PriceOfferImpl(origValue, offerValue));
        assertNotNull(product, "Product is null!");

        this.id = id;

        testBegin(ProductOfferWithOfferPriceImpl.class);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testMethod("formForSend()");

        ProductOfferPojo productPojo = (ProductOfferPojo) product.formForSend();
        assertNotNull(productPojo, "ProductOfferPojo is null!");
        System.out.println(mapper.writeValueAsString(productPojo));
    }

    @Test
    public void id() {
        testMethod("id()");

        assertEquals(product.id(), id);
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd(ProductOfferWithOfferPriceImpl.class);
    }
}
