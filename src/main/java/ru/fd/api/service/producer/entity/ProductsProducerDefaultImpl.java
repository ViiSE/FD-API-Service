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

import java.util.List;

@Service("productsProducerDefault")
public class ProductsProducerDefaultImpl implements ProductsProducer {

    private final ApplicationContext ctx;

    public ProductsProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Products getOrderProductsDefaultInstance(List<Product> products) {
        return (Products) ctx.getBean("orderProductsDefault", products);
    }

    @Override
    public Products getProductsDefaultInstance(ProductProducer productProducer, List<Product> products) {
        return (Products) ctx.getBean("productsDefault", productProducer, products);
    }

    @Override
    public Products getProductsFailedInstance(String errorMessage) {
        return (Products) ctx.getBean("productsFailed", errorMessage);
    }
}
