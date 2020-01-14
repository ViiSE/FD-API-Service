/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fd.api.service.OrdersService;
import ru.fd.api.service.constant.Processors;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.data.OrderResponsePojo;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.CreateOrderException;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;

@Controller("/orders")
@Api(tags="Orders Controller", description = "Контроллер точек для работы с заказами", authorizations = {@Authorization(value = "Bearer")})
public class OrdersController {

    private final OrdersService ordersService;

    private final LoggerService logger;

    public OrdersController(OrdersService ordersService, LoggerService logger) {
        this.ordersService = ordersService;
        this.logger = logger;
    }

    @ApiOperation(value = "Создает заказ", notes = "")
    @PostMapping
    @ResponseBody
    public OrderResponsePojo createOrder(@RequestBody OrderPojo orderPojo) {
        try {
            Order order = ordersService.orderCreatorProducer()
                    .getOrderCreatorFromBodyInstance(
                            orderPojo,
                            ordersService)
                    .create();
            OrderResponse orderResponse = ordersService.orderRepositoryProcessors()
                    .processor(Processors.CREATE_ORDER_WITHOUT_CHECK_STATUS)
                    .apply(order);
            OrderResponsePojo orderResponsePojo = (OrderResponsePojo) orderResponse.formForSend();

            if(orderResponsePojo.getStatus() == 500) {
                logger.error(DepartmentsController.class, orderResponsePojo.getExMessage());
                throw new CreateOrderException(orderResponsePojo.getMessage());
            } else {
                logger.info(OrdersController.class, "Order " + orderResponsePojo.getId() + " was created");
                return orderResponsePojo;
            }
        } catch (CreatorException ex) {
            logger.error(DepartmentsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            throw new CreateOrderException(ex.getMessage());
        }
    }
}
