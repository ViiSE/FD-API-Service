package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductPojo;
import test.creator.ProductCreatorTestImpl;

import static org.testng.Assert.*;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class ProductWithFullDescriptionTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    @BeforeClass
    public void setUpClass() {
        product = new ProductCreatorTestImpl().createProduct();
        assertNotNull(product, "Product is null!");
        testBegin("ProductWithFullDescription", "formForSend()");
    }

    @Test
    public void formForSend_fullDescription() throws JsonProcessingException {
        String fullDescription = "Full description";
        product = new ProductWithFullDescriptionImpl(product, fullDescription);
        assertNotNull(product, "Product with full description is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertNotNull(productPojo.getFullDescription(), "Full description is null!");
        assertEquals(productPojo.getFullDescription(), fullDescription);

        String sendForForm = mapper.writeValueAsString(productPojo);
        assertTrue(sendForForm.contains("\"full_description\":\"" + fullDescription + "\""));
        System.out.println(sendForForm);
    }

    @Test
    public void formForSend_fullDescriptionIsNull() throws JsonProcessingException {
        product = new ProductWithFullDescriptionImpl(product, null);
        assertNotNull(product, "Product with full description is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertTrue(productPojo.getFullDescription().isEmpty());

        String sendForForm = mapper.writeValueAsString(productPojo);
        assertTrue(sendForForm.contains("\"full_description\":\"\""));
        System.out.println(sendForForm);
    }

    @Test
    public void formForSend_fullDescriptionIsEmpty() throws JsonProcessingException {
        product = new ProductWithFullDescriptionImpl(product, "");
        assertNotNull(product, "Product with full description is null!");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertTrue(productPojo.getFullDescription().isEmpty());

        String sendForForm = mapper.writeValueAsString(productPojo);
        assertTrue(sendForForm.contains("\"full_description\":\"\""));
        System.out.println(sendForForm);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("ProductWithFullDescription", "formForSend()");
    }
}
