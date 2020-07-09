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

package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductChangedBalancesPojo;
import ru.fd.api.service.data.ProductPojo;
import ru.fd.api.service.data.ProductsChangedBalancesPojo;
import ru.fd.api.service.data.ProductsPojo;
import ru.fd.api.service.producer.entity.ProductProducer;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component("productsChangedBalancesWithRequestDateTime")
@Scope("prototype")
public class ProductsChangedBalancesWithRequestDateTimeImpl implements Products {

    private final Products products;
    private final ZonedDateTime requestDateTime;

    public ProductsChangedBalancesWithRequestDateTimeImpl(Products products, ZonedDateTime requestDateTime) {
        this.products = products;
        this.requestDateTime = requestDateTime;
    }

    @Override
    public Product findProductById(String id) {
        return products.findProductById(id);
    }

    @Override
    public void decorateProduct(String id, Product product) {
        products.decorateProduct(id, product);
    }

    @Override
    public void decorateProduct(int key, Product product) {
        products.decorateProduct(key, product);
    }

    @Override
    public void removeProducts(Class<? extends Product> decorateProduct) {
        products.removeProducts(decorateProduct);
    }

    @Override
    public Object formForSend() {
        ProductsChangedBalancesPojo chBalPojo = (ProductsChangedBalancesPojo) products.formForSend();
        chBalPojo.setFrom(requestDateTime);

        return chBalPojo;
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }

    @Override
    public void forEach(Consumer<? super Product> action) {
        products.forEach(action);
    }
}
