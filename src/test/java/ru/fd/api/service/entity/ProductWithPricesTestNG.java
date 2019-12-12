package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductPojo;
import test.creator.PricesCreatorTestImpl;
import test.creator.ProductCreatorTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class ProductWithPricesTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    @BeforeClass
    public void setUpClass() {
        product = new ProductCreatorTestImpl().createProduct();
        assertNotNull(product, "Product is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("ProductWithPrices", "formForSend()");

        Prices prices = new PricesCreatorTestImpl().createPrices();
        product = new ProductWithPricesImpl(product, prices);
        assertNotNull(product, "Product with prices is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertNotNull(productPojo.getPrices(), "Prices is null!");
        assertTrue(mapper.writeValueAsString(prices.formForSend()).contains(mapper.writeValueAsString(productPojo.getPrices())));

        System.out.println(mapper.writeValueAsString(productPojo));

        testEnd("ProductWithPrices", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
