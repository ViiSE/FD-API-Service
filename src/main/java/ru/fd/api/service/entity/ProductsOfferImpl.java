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
import ru.fd.api.service.data.ProductOfferPojo;
import ru.fd.api.service.data.ProductsOfferPojo;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component("productsOffer")
@Scope("prototype")
public class ProductsOfferImpl implements Products {

    private final List<Product> products;

    public ProductsOfferImpl(List<Product> products) {
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
        // Not supported
    }

    @Override
    public Object formForSend() {
        List<ProductOfferPojo> productPojos = products.stream()
                .map(product -> (ProductOfferPojo) product.formForSend())
                .collect(Collectors.toList());
        return new ProductsOfferPojo(productPojos);
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
