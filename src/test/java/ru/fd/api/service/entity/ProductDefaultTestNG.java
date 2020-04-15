package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.fd.api.service.data.ProductPojo;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class ProductDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Product product;

    private String id;
    private String categoryId;
    private String vendorId;
    private String unitId;
    private String name;
    private short tax;
    private String articul;
    private String code;

    @BeforeClass
    @Parameters({"id", "categoryId", "vendorId", "unitId", "name", "tax", "articul", "code"})
    public void setUpClass(
            String id, String categoryId, String vendorId, String unitId,
            String name, short tax, String articul, String code) {
        assertNotNull(id, "ID cannot be null!");
        assertFalse(id.isEmpty(), "ID is empty!");

        assertNotNull(categoryId, "Category ID cannot be null!");
        assertFalse(categoryId.isEmpty(), "Category ID is empty!");

        assertNotNull(vendorId, "Vendor ID cannot be null!");
        assertFalse(vendorId.isEmpty(), "Vendor ID is empty!");

        assertNotNull(name, "Name cannot be null!");
        assertFalse(name.isEmpty(), "Name is empty!");

        assertNotNull(unitId, "Unit ID cannot be null!");
        assertFalse(unitId.isEmpty(), "Unit ID is empty!");

        assertFalse(tax < 0, "Tax is less than 0!");

        assertNotNull(articul, "Articul cannot be null!");
        assertFalse(articul.isEmpty(), "Articul is empty!");

        assertNotNull(code, "Code cannot be null!");
        assertFalse(code.isEmpty(), "Code is empty!");

        product = new ProductImpl(id, categoryId, vendorId, unitId, name, tax, articul, code);
        assertNotNull(product, "Product is null!");

        this.id = id;
        this.categoryId = categoryId;
        this.vendorId = vendorId;
        this.unitId = unitId;
        this.name = name;
        this.tax = tax;
        this.articul = articul;
        this.code = code;

        testBegin("ProductSimple");
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testMethod("formForSend()");

        ProductPojo productPojo = (ProductPojo) product.formForSend();
        assertNotNull(productPojo, "ProductPojo is null!");
        assertEquals(productPojo.getId(), id);
        assertEquals(productPojo.getCategoryId(), categoryId);
        assertEquals(productPojo.getVendorId(), vendorId);
        assertEquals(productPojo.getName(), name);
        assertEquals(productPojo.getUnitId(), unitId);
        assertEquals(productPojo.getTax(), tax);
        assertEquals(productPojo.getArticul(), articul);
        assertEquals(productPojo.getCode(), code);
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
        testEnd("ProductSimple");
    }
}
