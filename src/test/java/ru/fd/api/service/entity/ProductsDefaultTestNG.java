/*
 * Copyright 2020 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.data.ProductsPojo;
import ru.fd.api.service.process.test.PsProductTestImpl;
import ru.fd.api.service.process.test.PsProductsTestImpl;

import static org.testng.Assert.*;
import static test.message.TestMessage.*;

public class ProductsDefaultTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private Products products;
    private Product product;

    @BeforeClass
    public void setUpClass() {
        product = new PsProductTestImpl().answer(null);
        products = new PsProductsTestImpl().answer(null);

        assertNotNull(products, "Products is null!");
        testBegin("ProductsDefault");
    }

    @Test(priority = 1)
    public void formForSend() throws JsonProcessingException {
        testMethod("formForSend()");

        ProductsPojo productsPojo = (ProductsPojo) products.formForSend();
        assertNotNull(productsPojo, "ProductPojo is null!");
        assertNotNull(productsPojo.getProducts());

        System.out.println(mapper.writeValueAsString(productsPojo));
    }

    @Test(priority = 2)
    public void findProductById() throws JsonProcessingException {
        testMethod("findProductById()");

        Product foundProduct = products.findProductById(product.id());
        assertNotNull(foundProduct, "Found Product is null!");
        assertEquals(mapper.writeValueAsString(foundProduct.formForSend()), mapper.writeValueAsString(product.formForSend()));

        System.out.println("--Product to be found--");
        System.out.println(mapper.writeValueAsString(product.formForSend()));
        System.out.println("--Found product--");
        System.out.println(mapper.writeValueAsString(foundProduct.formForSend()));
    }

    @Test(priority = 3)
    public void decorateProduct() throws JsonProcessingException {
        testMethod("decorateProduct()");

        Products oldProducts = new PsProductsTestImpl().answer(null);

        System.out.println("--Old product--");
        System.out.println(mapper.writeValueAsString(product.formForSend()));

        Product productWithFullDescription = new ProductWithFullDescriptionImpl(product, "Full description");

        System.out.println("--New product--");
        System.out.println(mapper.writeValueAsString(productWithFullDescription.formForSend()));

        products.decorateProduct(product.id(), productWithFullDescription);
        assertEquals(productWithFullDescription, products.findProductById(productWithFullDescription.id()));
        assertNotEquals(mapper.writeValueAsString(oldProducts.formForSend()), mapper.writeValueAsString(products.formForSend()));

        System.out.println("--New products--");
        System.out.println(mapper.writeValueAsString(products.formForSend()));
    }

    @Test(priority = 4)
    public void removeProducts() throws JsonProcessingException {
        testMethod("removeProducts()");

        System.out.println("--Old products--");
        System.out.println(mapper.writeValueAsString(products.formForSend()));

        products.removeProducts(ProductSimpleImpl.class);

        for(Product pr: products)
            System.out.println(pr);


        for(Product pr: products)
            assertFalse(pr instanceof ProductWithFullDescriptionImpl, "Product not removed!");

        System.out.println("--New products--");
        System.out.println(mapper.writeValueAsString(products.formForSend()));
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterTest
    public void teardownClass() {
        testEnd("ProductsDefault");
    }

    private Product createProduct() {
        String id = "id_1";
        String categoryId = "category_1";
        String vendorId = "vendor_1";
        String unitId = "unit_1";
        String name = "Item_1";
        short tax = 20;
        String articul = "art_1";
        String code = "code_1";
        return new ProductImpl(id, categoryId, vendorId, unitId, name, tax, articul, code);
    }
}
