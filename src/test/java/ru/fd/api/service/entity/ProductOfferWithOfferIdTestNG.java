package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.fd.api.service.data.ProductOfferPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class ProductOfferWithOfferIdTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    private String id;
    private long offerId;

    @BeforeClass
    @Parameters({"id", "offerId"})
    public void setUpClass(String id, long offerId) {
        assertNotNull(id, "ID cannot be null!");
        assertFalse(id.isEmpty(), "ID is empty!");

        product = new ProductOfferWithOfferIdImpl(
                new ProductOfferWithIdImpl(id),
                offerId);
        assertNotNull(product, "Product is null!");

        this.id = id;
        this.offerId = offerId;

        testBegin(ProductOfferWithOfferIdImpl.class);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testMethod("formForSend()");

        ProductOfferPojo productPojo = (ProductOfferPojo) product.formForSend();
        assertNotNull(productPojo, "ProductOfferPojo is null!");
        assertEquals(productPojo.getId(), id);
        assertEquals(productPojo.getOfferId(), offerId);
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
        testEnd(ProductOfferWithOfferIdImpl.class);
    }
}
