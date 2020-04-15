/*
 * Copyright 2019 ViiSE
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

package ru.fd.api.service.process;

import org.springframework.stereotype.Component;
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.data.DeliveryPojo;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.entity.Delivery;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreateOrderBadRequestException;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.producer.entity.OrderProducer;

@Component("psOrderFromBody")
public class PsOrderFromBodyImpl implements Process<Order, OrderPojo> {

    private final OrderProducer orderProducer;
    private final Process<Delivery, DeliveryPojo> delProc;
    private final Process<Customer, CustomerPojo> cstProc;
    private final Process<Products, OrderPojo> oProdProc;

    public PsOrderFromBodyImpl(
            OrderProducer orderProducer,
            Process<Delivery, DeliveryPojo> delProc,
            Process<Customer, CustomerPojo> cstProc,
            Process<Products, OrderPojo> oProdProc) {
        this.orderProducer = orderProducer;
        this.delProc = delProc;
        this.cstProc = cstProc;
        this.oProdProc = oProdProc;
    }

    @Override
    public Order answer(OrderPojo orderPojo) throws ProcessException {
        checkOrder(orderPojo);

        Order order = orderProducer.getOrderSimpleInstance(orderPojo.getId(), orderPojo.getStatus());
        order = orderProducer.getOrderWithCityIdInstance(order, orderPojo.getCityId());
        order = orderProducer.getOrderWithPayTypeIdInstance(order, orderPojo.getPayTypeId());
        order = orderProducer.getOrderWithDateTimeInstance(order, orderPojo.getDateTime());
        order = orderProducer.getOrderWithDeliveryInstance(
                order,
                delProc.answer(orderPojo.getDelivery()));
        order = orderProducer.getOrderWithCustomerInstance(
                order,
                cstProc.answer(orderPojo.getCustomer()));

        if(orderPojo.getProducts() != null && !(orderPojo.getProducts().isEmpty()))
            order = orderProducer.getOrderWithProductsInstance(
                    order,
                    oProdProc.answer(orderPojo));
        else {
            String message = "Order: products required";
            throw new ProcessException(message, new CreateOrderBadRequestException(message));
        }
        if(orderPojo.getComment().isEmpty())
            return order;
        else
            return orderProducer.getOrderWithCommentInstance(order, orderPojo.getComment());
    }

    private void checkOrder(OrderPojo orderPojo) throws ProcessException {
        if(orderPojo == null) {
            String message = "Order required";
            throw new ProcessException(message, new CreateOrderBadRequestException(message));
        }

        if(orderPojo.getId() < 0) {
            String message = "Order: id required";
            throw new ProcessException(message, new CreateOrderBadRequestException(message));
        }

        if(orderPojo.getPayTypeId() != 0 && orderPojo.getPayTypeId() != 1) {
            String message = "Order: pay type id required";
            throw new ProcessException(message, new CreateOrderBadRequestException(message));
        }

        if(orderPojo.getCityId() < 0) {
            String message = "Order: city id required";
            throw new ProcessException(message, new CreateOrderBadRequestException(message));
        }
    }
}
