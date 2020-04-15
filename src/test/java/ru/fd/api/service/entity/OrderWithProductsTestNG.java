/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.data.ProductsOrderPojo;
import test.util.TestUtils;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class OrderWithProductsTestNG {

    private Order order;
    private long id;
    private short status;
    private Products orderProducts;

    @BeforeClass
    @Parameters({"id", "status"})
    public void setUpClass(long id, short status) {
        this.id = id;
        this.status = status;
        orderProducts = new OrderProductsImpl(new ArrayList<>() {{
            add(new OrderProductSimpleImpl("id1", 5));
            add(new OrderProductSimpleImpl("id2", 10));
        }});

        order = new OrderWithProductsImpl(new OrderSimpleImpl(id, status), orderProducts);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("OrderWithProducts", "formForSend()");

        OrderPojo orderPojo = new OrderPojo(id);
        orderPojo.setStatus(status);
        ProductsOrderPojo productsOrderPojo = (ProductsOrderPojo) orderProducts.formForSend();
        orderPojo.setProducts(productsOrderPojo.getProducts());

        ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();
        assertEquals(
                objectMapper.writeValueAsString(order.formForSend()),
                objectMapper.writeValueAsString(orderPojo));

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));
        System.out.println("Done!");

        testEnd("OrderWithProducts", "formForSend()");
    }
}
