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
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;

import java.util.stream.Collectors;

@Component("psOrderProducts")
public class PsOrderProductsImpl implements Process<Products, OrderPojo> {

    private final ProductsProducer orderProductsProducer;
    private final ProductProducer productProducer;

    public PsOrderProductsImpl(
            ProductsProducer orderProductsProducer,
            ProductProducer productProducer) {
        this.orderProductsProducer = orderProductsProducer;
        this.productProducer = productProducer;
    }

    @Override
    public Products answer(OrderPojo orderPojo) throws ProcessException {
        return orderProductsProducer.getOrderProductsDefaultInstance(
                orderPojo.getProducts()
                        .stream()
                        .map(product -> productProducer
                                .getOrderProductSimpleInstance(
                                        product.getId(),
                                        product.getQuantity(),
                                        product.getSumPrice()))
                        .collect(Collectors.toList()));
    }
}
