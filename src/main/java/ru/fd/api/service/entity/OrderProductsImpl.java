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
import ru.fd.api.service.data.ProductOrderPojo;
import ru.fd.api.service.data.ProductsOrderPojo;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component("orderProducts")
@Scope("prototype")
public class OrderProductsImpl implements Products {

    private final List<Product> products;

    public OrderProductsImpl(List<Product> products) {
        this.products = products;
    }

    @Override
    public Object formForSend() {
        List<ProductOrderPojo> productPojos = products.stream()
                .map(product -> (ProductOrderPojo) product.formForSend())
                .collect(Collectors.toList());
        return new ProductsOrderPojo(productPojos);
    }

    @Override
    public Product findProductById(String id) {
        for (Product product : products) {
            if (product.id().equals(id))
                return product;
        }
        return null;
    }

    @Override
    public void decorateProduct(String id, Product product) {
        // nothing
    }

    @Override
    public void decorateProduct(int key, Product product) {
        // nothing
    }

    @Override
    public void removeProducts(Class<? extends Product> decorateProduct) {
        // nothing
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
