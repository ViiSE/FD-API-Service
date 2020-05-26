package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.fd.api.service.data.ProductOfferPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class ProductOfferWithIdTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    private String id;

    @BeforeClass
    @Parameters({"id"})
    public void setUpClass(String id) {
        assertNotNull(id, "ID cannot be null!");
        assertFalse(id.isEmpty(), "ID is empty!");

        product = new ProductOfferWithIdImpl(id);
        assertNotNull(product, "Product is null!");

        this.id = id;

        testBegin(ProductOfferWithIdImpl.class);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testMethod("formForSend()");

        ProductOfferPojo productPojo = (ProductOfferPojo) product.formForSend();
        assertNotNull(productPojo, "ProductOfferPojo is null!");
        assertEquals(productPojo.getId(), id);
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
        testEnd(ProductOfferWithIdImpl.class);
    }
}
