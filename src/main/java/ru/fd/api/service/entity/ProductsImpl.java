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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component("products")
@Scope("prototype")
public class ProductsImpl implements Products {

    private final ProductProducer productProducer;
    private final List<Product> products;

    public ProductsImpl(ProductProducer productProducer, List<Product> products) {
        this.productProducer = productProducer;
        this.products = products;
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
        int index = products.indexOf(findProductById(id));
        products.set(index, product);
    }

    @Override
    public void decorateProduct(int key, Product product) {
        products.set(key, product);
    }

    @Override
    public void removeProducts(Class<? extends Product> decorateProduct) {
        try {
            int key = 0;
            List<Product> decorateProducts = new ArrayList<>();
            for (Product product : products)
                if (Class.forName(decorateProduct.getName()).isInstance(product))
                    decorateProducts.add(productProducer.getProductSimpleInstance(product, key++));
            products.clear();
            products.addAll(decorateProducts);
        } catch (ClassNotFoundException ignore) {}
    }

    // FIXME: 28.01.2020 CREATE IMPL INSTEAD IF-ELSE
    @Override
    public Object formForSend() {
        if(!products.isEmpty()) {
            if(products.get(0) instanceof ProductWithChangedBalancesImpl) {
                List<ProductChangedBalancesPojo> productPojos = products.stream()
                        .map(product -> (ProductChangedBalancesPojo) product.formForSend())
                        .collect(Collectors.toList());
                return new ProductsChangedBalancesPojo(productPojos);
            } else {
                List<ProductPojo> productPojos = products.stream()
                        .map(product -> (ProductPojo) product.formForSend())
                        .collect(Collectors.toList());
                return new ProductsPojo(productPojos);
            }
        }

        return new ProductsPojo(new ArrayList<>());
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
