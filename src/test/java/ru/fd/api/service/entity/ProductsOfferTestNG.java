package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.fd.api.service.data.ProductsOfferPojo;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static test.message.TestMessage.*;

public class ProductsOfferTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Products products;

    @BeforeClass
    @Parameters({"id1", "id2", "offerId1", "offerId2", "origValue1", "origValue2", "offerValue1", "offerValue2"})
    public void setUpClass(String id1, String id2, long offerId1, long offerId2, float origValue1, float origValue2, float offerValue1, float offerValue2) {
        assertNotNull(id1, "ID cannot be null!");
        assertFalse(id1.isEmpty(), "ID is empty!");

        assertNotNull(id2, "ID cannot be null!");
        assertFalse(id2.isEmpty(), "ID is empty!");

        assertFalse(offerId1 < 0L, "Offer ID is less than 0!");
        assertFalse(offerId2 < 0L, "Offer ID is less than 0!");

        assertFalse(origValue1 < 0.f, "Original value price is less than 0!");
        assertFalse(offerValue1 < 0.f, "Offer value price is less than 0!");

        assertFalse(origValue2 < 0.f, "Original value price is less than 0!");
        assertFalse(offerValue2 < 0.f, "Offer value price is less than 0!");

        products = new ProductsOfferImpl(
                new ArrayList<>() {{
                    add(new ProductOfferWithOfferIdImpl(
                            new ProductOfferWithOfferPriceImpl(
                                    new ProductOfferWithIdImpl(id1),
                                    new PriceOfferImpl(origValue1, offerValue1)),
                            offerId1));
                    add(new ProductOfferWithOfferIdImpl(
                            new ProductOfferWithOfferPriceImpl(
                                    new ProductOfferWithIdImpl(id2),
                                    new PriceOfferImpl(origValue2, offerValue2)),
                            offerId2));
                }}
        );

        assertNotNull(products, "Product is null!");

        testBegin(ProductsOfferImpl.class);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testMethod("formForSend()");

        ProductsOfferPojo productsPojo = (ProductsOfferPojo) products.formForSend();
        assertNotNull(productsPojo, "ProductsOfferPojo is null!");
        System.out.println(mapper.writeValueAsString(productsPojo));
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd(ProductsOfferImpl.class);
    }
}
