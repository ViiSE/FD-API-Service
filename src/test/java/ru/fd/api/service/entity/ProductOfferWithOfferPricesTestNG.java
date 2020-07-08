package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.fd.api.service.data.ProductOfferPojo;

import java.util.ArrayList;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class ProductOfferWithOfferPricesTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    private String id;

    @BeforeClass
    @Parameters({"id", "origValue", "offerValue", "depId"})
    public void setUpClass(String id, float origValue, float offerValue, String depId) {
        assertNotNull(depId, "Department ID cannot be null!");
        assertFalse(depId.isEmpty(), "Department ID is empty!");
        assertNotNull(id, "ID cannot be null!");
        assertFalse(id.isEmpty(), "ID is empty!");

        assertFalse(origValue < 0.f, "Original value price is less than 0!");
        assertFalse(offerValue < 0.f, "Offer value price is less than 0!");

        product = new ProductOfferWithOfferPricesImpl(
                new ProductOfferWithIdImpl(id),
                new PricesOfferImpl(new ArrayList<>() {{
                    add(new PriceOfferWithDepartmentIdImpl(
                            new PriceOfferImpl(origValue, offerValue), depId));
                }}));
        assertNotNull(product, "Product is null!");

        this.id = id;

        testBegin(ProductOfferWithOfferPricesImpl.class);
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
        testEnd(ProductOfferWithOfferPricesImpl.class);
    }
}
