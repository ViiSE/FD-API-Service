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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.data.ProductsOrderPojo;
import ru.fd.api.service.exception.CreatorException;
import test.creator.CustomerCreatorTestImpl;
import test.creator.DeliveryCreatorTestImpl;
import test.creator.OrderProductsCreatorTest;
import test.util.TestUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.assertEquals;
import static test.message.TestMessage.testBegin;
import static test.message.TestMessage.testEnd;

public class OrderWithProductsTestNG {

    private Order order;
    private long id;
    private String cityId;
    private Customer customer;
    private Delivery delivery;
    private short payTypeId;
    private LocalDateTime dateTime;
    private Products orderProducts;

    @BeforeClass
    @Parameters({"id", "cityId", "payTypeId"})
    public void setUpClass(long id, String cityId, short payTypeId) throws CreatorException {
        this.id = id;
        this.cityId = cityId;
        this.customer = new CustomerCreatorTestImpl().create();
        this.delivery = new DeliveryCreatorTestImpl().create();
        this.payTypeId = payTypeId;
        this.dateTime = LocalDateTime.now();

        orderProducts = new OrderProductsCreatorTest().create();

        order = new OrderWithProductsImpl(
                new OrderSimpleImpl(
                        id,
                        cityId,
                        customer,
                        delivery,
                        payTypeId,
                        dateTime),
                orderProducts);
    }

    @Test
    public void formForSend() throws JsonProcessingException {
        testBegin("OrderWithProducts", "formForSend()");

        OrderPojo orderPojo = new OrderPojo(
                id,
                cityId,
                (CustomerPojo) customer.formForSend(),
                (DeliveryPojo) delivery.formForSend(),
                payTypeId,
                dateTime);
        ProductsOrderPojo productsOrderPojo = (ProductsOrderPojo) orderProducts.formForSend();
        orderPojo.setProducts(productsOrderPojo.getProducts());

        ObjectMapper objectMapper = TestUtils.objectMapperWithJavaTimeModule();
        assertEquals(
                objectMapper.writeValueAsString(order.formForSend()),
                objectMapper.writeValueAsString(orderPojo));

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));

        String json =
                "{\"id\":1," +
                "\"city_id\":\"cId1\"," +
                "\"customer\":{\"phone_number\":\"89098238724\"}," +
                "\"delivery\":{\"type\":0,\"city_id\":\"cId1\",\"address\":\"st. example\"}," +
                "\"pay_type_id\":0," +
                "\"products\":[{\"id\":\"id_1\",\"quantity\":10},{\"id\":\"id_1\",\"quantity\":10}]," +
                "\"comment\":\"hi\"," +
                "\"date_time\":\"2020-01-10 09:06:30\"}";
        OrderPojo orderPojod = objectMapper.readValue(json, OrderPojo.class);

        System.out.println("Done!");

        testEnd("OrderWithProducts", "formForSend()");
    }
}
