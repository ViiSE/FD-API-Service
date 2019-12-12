package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductPojo;
import ru.fd.api.service.exception.CreatorException;
import test.creator.AttributesCreatorTestImpl;
import test.creator.ProductCreatorTestImpl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class ProductWithAttributesTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    @BeforeClass
    public void setUpClass() {
        product = new ProductCreatorTestImpl().createProduct();
        assertNotNull(product, "Product is null!");
    }

    @Test
    public void formForSend() throws JsonProcessingException, CreatorException {
        testBegin("ProductWithAttributes", "formForSend()");

        Attributes attributes = new AttributesCreatorTestImpl().create();
        product = new ProductWithAttributesImpl(product, attributes);
        assertNotNull(product, "Product with attributes is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertNotNull(productPojo.getAttributes(), "Attributes is null!");
        assertTrue(mapper.writeValueAsString(attributes.formForSend()).contains(mapper.writeValueAsString(productPojo.getAttributes())));

        System.out.println(mapper.writeValueAsString(productPojo));

        testEnd("ProductWithAttributes", "formForSend()");
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }
}
