/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.creator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.fd.api.service.OrdersService;
import ru.fd.api.service.data.*;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.exception.CreatorException;
import test.OrdersServiceTestImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.testng.Assert.assertTrue;
import static test.message.TestMessage.*;

public class OrderCreatorFromBodyTestNG {

    private final ObjectMapper mapper = new ObjectMapper();
    private OrdersService ordersService;

    @BeforeClass
    public void setUpClass() {
        ordersService = new OrdersServiceTestImpl();

        testBegin("OrderCreatorFromBody");
    }

    @Test(priority = 1)
    public void create_withComment() throws CreatorException, JsonProcessingException {
        testMethod("create() [with comment]");

        OrderPojo orderPojo = createOrderWithProducts("Example comment");
        test(orderPojo);
    }

    @Test(priority = 2)
    public void create_withoutCommentNull() throws CreatorException, JsonProcessingException {
        testMethod("create() [without comment (null)]");

        OrderPojo orderPojo = createOrderWithProducts(null);
        test(orderPojo);
    }

    @Test(priority = 3)
    public void create_withoutCommentEmpty() throws CreatorException, JsonProcessingException {
        testMethod("create() [without comment (empty)]");

        OrderPojo orderPojo = createOrderWithProducts("");
        test(orderPojo);
    }

    @Test(priority = 4, expectedExceptions = CreatorException.class, expectedExceptionsMessageRegExp = "Products required")
    public void create_withoutProducts() throws CreatorException {
        testMethod("create() [without products]");

        OrderPojo orderPojo = createOrderWithoutProducts();
        new OrderCreatorFromBodyImpl(orderPojo, ordersService).create();
    }

    @Test(priority = 5, expectedExceptions = CreatorException.class, expectedExceptionsMessageRegExp = "Order: city id required*")
    public void create_withoutCityId() throws CreatorException {
        testMethod("create() [without cityId]");

        OrderPojo orderPojo = createOrderWithoutCityId();
        new OrderCreatorFromBodyImpl(orderPojo, ordersService).create();
    }

    @AfterMethod
    public void getRunTime(ITestResult tr) {
        printTestTime(tr);
    }

    @AfterClass
    public void teardownClass() {
        testEnd("OrderCreatorFromBody");
    }

    private OrderPojo createOrder(String comment, String cityId) {
        OrderPojo orderPojo = new OrderPojo(
                1L,
                cityId,
                new CustomerPojo() {{
                    setName("Customer");
                    setEmail("example@example.com"); }},
                new DeliveryPojo((short) 0, "cId1", "Example st.") {{
                    setDeliveryTimeId((short) 0);
                    setDeliveryDate(LocalDate.now().plusDays(1));
                    setDepartmentId("depId1");}},
                (short) 0,
                LocalDateTime.now());
        orderPojo.setComment(comment);

        return orderPojo;
    }

    private OrderPojo createOrderWithProducts(String comment) {
       OrderPojo orderPojo = createOrder(comment, "cId1");
       orderPojo.setProducts(
               new ProductsOrderPojo(
                       new ArrayList<>() {{
                           add(new ProductOrderPojo("item1", 10));
                           add(new ProductOrderPojo("item2", 20));
                       }})
                       .getProducts());
       orderPojo.setComment(comment);

       return orderPojo;
    }

    private OrderPojo createOrderWithoutProducts() {
        return createOrder("", "cId1");
    }

    private OrderPojo createOrderWithoutCityId() {
        return createOrder("", "");
    }

    private void test(OrderPojo orderPojo) throws JsonProcessingException, CreatorException {
        Order order = new OrderCreatorFromBodyImpl(orderPojo, ordersService).create();

        assertTrue(mapper.writeValueAsString(order.formForSend()).contains("\"id\""));
        assertTrue(mapper.writeValueAsString(order.formForSend()).contains("\"city_id\""));
        assertTrue(mapper.writeValueAsString(order.formForSend()).contains("\"customer\""));
        assertTrue(mapper.writeValueAsString(order.formForSend()).contains("\"delivery\""));
        assertTrue(mapper.writeValueAsString(order.formForSend()).contains("\"pay_type_id\""));
        assertTrue(mapper.writeValueAsString(order.formForSend()).contains("\"date_time\""));
        assertTrue(mapper.writeValueAsString(order.formForSend()).contains("\"products\""));
        assertTrue(mapper.writeValueAsString(order.formForSend()).contains("\"comment\""));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(order.formForSend()));
    }
}
