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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.fd.api.service.OrdersService;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.producer.creator.CustomerCreatorProducer;
import ru.fd.api.service.producer.creator.DeliveryCreatorProducer;
import ru.fd.api.service.producer.creator.ProductsCreatorProducer;
import ru.fd.api.service.producer.entity.*;

@Service("orderCreatorFromBody")
@Scope("prototype")
public class OrderCreatorFromBodyImpl implements OrderCreator {

    private final OrderPojo orderPojo;
    private final OrderProducer orderProducer;
    private final ProductsCreatorProducer productsCreatorProducer;
    private final ProductProducer productProducer;
    private final ProductsProducer productsProducer;
    private final DeliveryCreatorProducer deliveryCreatorProducer;
    private final DeliveryProducer deliveryProducer;
    private final CustomerCreatorProducer customerCreatorProducer;
    private final CustomerProducer customerProducer;

    public OrderCreatorFromBodyImpl(OrderPojo orderPojo, OrdersService ordersService) {
        this.orderPojo = orderPojo;
        this.orderProducer = ordersService.orderProducer();
        this.productsCreatorProducer = ordersService.productsCreatorProducer();
        this.productProducer = ordersService.productProducer();
        this.productsProducer = ordersService.productsProducer();
        this.deliveryCreatorProducer = ordersService.deliveryCreatorProducer();
        this.deliveryProducer = ordersService.deliveryProducer();
        this.customerCreatorProducer = ordersService.customerCreatorProducer();
        this.customerProducer = ordersService.customerProducer();
    }

    @Override
    public Order create() throws CreatorException {
        checkOrder();

        Order order = orderProducer.getOrderSimpleInstance(
                orderPojo.getId(),
                orderPojo.getCityId(),
                customerCreatorProducer
                        .getCustomerCreatorEmailOrPhoneRequiredInstance(orderPojo.getCustomer(), customerProducer)
                        .create(),
                deliveryCreatorProducer
                        .getDeliveryCreatorDefaultInstance(orderPojo.getDelivery(), deliveryProducer)
                        .create(),
                orderPojo.getPayTypeId(),
                orderPojo.getDateTime());

        if(orderPojo.getProducts() != null && !(orderPojo.getProducts().isEmpty()))
            order = orderProducer.getOrderWithProductsInstance(
                    order,
                    productsCreatorProducer
                            .getOrderProductsCreatorDefaultInstance(orderPojo, productsProducer, productProducer)
                            .create());
        else
            throw new CreatorException("Products required");

        if(orderPojo.getComment().isEmpty())
            return order;
        else
            return orderProducer.getOrderWithCommentInstance(order, orderPojo.getComment());
    }

    private void checkOrder() throws CreatorException {
        if(orderPojo == null)
            throw new CreatorException("Order required");

        if(orderPojo.getCityId().isEmpty())
            throw new CreatorException("Order: city id required");
    }
}
