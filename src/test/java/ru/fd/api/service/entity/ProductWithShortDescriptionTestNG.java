package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductPojo;
import test.producer.ProductCreatorTestImpl;

import static org.testng.Assert.*;
import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class ProductWithShortDescriptionTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    @BeforeClass
    public void setUpClass() {
        product = new ProductCreatorTestImpl().createProduct();
        assertNotNull(product, "Product is null!");
        testBegin("ProductWithShortDescription", "formForSend()");
    }

    @Test
    public void formForSend_shortDescription() throws JsonProcessingException {
        String shortDescription = "Sh des";
        product = new ProductWithShortDescriptionImpl(product, shortDescription);
        assertNotNull(product, "Product with short description is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertNotNull(productPojo.getShortDescription(), "Short description is null!");
        assertEquals(productPojo.getShortDescription(), shortDescription);

        String sendForForm = mapper.writeValueAsString(productPojo);
        assertTrue(sendForForm.contains("\"short_description\":\"" + shortDescription + "\""));
        System.out.println(sendForForm);
    }

    @Test
    public void formForSend_shortDescriptionIsNull() throws JsonProcessingException {
        product = new ProductWithShortDescriptionImpl(product, null);
        assertNotNull(product, "Product with short description is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertTrue(productPojo.getShortDescription().isEmpty());

        String sendForForm = mapper.writeValueAsString(productPojo);
        assertTrue(sendForForm.contains("\"short_description\":\"\""));
        System.out.println(sendForForm);
    }

    @Test
    public void formForSend_shortDescriptionIsEmpty() throws JsonProcessingException {
        product = new ProductWithShortDescriptionImpl(product, "");
        assertNotNull(product, "Product with short description is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertTrue(productPojo.getFullDescription().isEmpty());

        String sendForForm = mapper.writeValueAsString(productPojo);
        assertTrue(sendForForm.contains("\"short_description\":\"\""));
        System.out.println(sendForForm);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("ProductWithShortDescription", "formForSend()");
    }
}
