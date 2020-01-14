/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductOrderPojo;
import ru.fd.api.service.data.ProductsOrderPojo;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component("orderProductsDefault")
@Scope("prototype")
public class OrderProductsDefaultImpl implements Products {

    private final List<Product> products;

    public OrderProductsDefaultImpl(List<Product> products) {
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
