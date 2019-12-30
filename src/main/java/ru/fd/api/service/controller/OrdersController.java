/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.fd.api.service.SQLQueryCreatorService;
import ru.fd.api.service.constant.Processors;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.data.OrderResponsePojo;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;

@Controller
public class OrdersController {

    private final OrdersService ordersService;

    private final SQLQueryCreatorService sqlQueryCreatorService;
    private final LoggerService logger;

    public OrdersController(
            OrdersService ordersService,
            SQLQueryCreatorService sqlQueryCreatorService,
            LoggerService logger) {
        this.ordersService = ordersService;
        this.sqlQueryCreatorService = sqlQueryCreatorService;
        this.logger = logger;
    }

    @PostMapping("/orders")
    public OrderResponsePojo createOrder(@RequestBody OrderPojo orderPojo) {
        try {
            Order order = ordersService.orderCreatorProducer()
                    .getOrderCreatorFromBodyInstance(orderPojo)
                    .create();
            OrderResponsePojo orderResponsePojo = ordersService.orderRepositoryProcessors
                    .get(Processors.CREATE_ORDER)
                    .apply(order);
            logger.info(OrdersController.class, "Order " + orderResponsePojo.getId() + " was created");
            return orderResponsePojo;
        } catch (CreatorException ex) {
            logger.error(DepartmentsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return orderService.orderCreatorProducer()
                    .getOrderWithErrorInstance(ex.getMessage())
                    .create();
        }
    }

}
