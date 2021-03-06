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

package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Product;
import ru.fd.api.service.entity.Products;

import java.time.ZonedDateTime;
import java.util.List;

@Service("productsProducer")
public class ProductsProducerImpl implements ProductsProducer {

    private final ApplicationContext ctx;

    public ProductsProducerImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Products getOrderProductsInstance(List<Product> products) {
        return (Products) ctx.getBean("orderProducts", products);
    }

    @Override
    public Products getProductsInstance(ProductProducer productProducer, List<Product> products) {
        return (Products) ctx.getBean("products", productProducer, products);
    }

    @Override
    public Products getProductsChangedBalancesWithRequestDateTimeInstance(Products products, ZonedDateTime requestDateTime) {
        return (Products) ctx.getBean("productsChangedBalancesWithRequestDateTime", products, requestDateTime);
    }

    @Override
    public Products getProductsOfferInstance(List<Product> products) {
        return (Products) ctx.getBean("productsOffer", products);
    }
}
