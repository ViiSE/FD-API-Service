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
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;

import java.util.stream.Collectors;

@Component("orderProductsCreatorDefault")
@Scope("prototype")
public class OrderProductsCreatorDefaultImpl implements ProductsCreator {

    private final OrderPojo order;
    private final ProductsProducer orderProductsProducer;
    private final ProductProducer productProducer;

    public OrderProductsCreatorDefaultImpl(
            OrderPojo order,
            ProductsProducer orderProductsProducer,
            ProductProducer productProducer) {
        this.order = order;
        this.orderProductsProducer = orderProductsProducer;
        this.productProducer = productProducer;
    }

    @Override
    public Products create() throws CreatorException {
        return orderProductsProducer.getOrderProductsDefaultInstance(
                order.getProducts()
                        .stream()
                        .map(product -> productProducer
                                .getOrderProductSimpleInstance(
                                        product.getId(),
                                        product.getQuantity()))
                        .collect(Collectors.toList()));
    }
}
