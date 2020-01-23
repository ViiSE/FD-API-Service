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
import ru.fd.api.service.data.ProductPojo;
import ru.fd.api.service.data.ProductsPojo;
import ru.fd.api.service.producer.entity.ProductProducer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component("productsFailed")
@Scope("prototype")
public class ProductsFailedImpl implements Products {

    private final String errorMessage;

    public ProductsFailedImpl(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Product findProductById(String id) {
        throw new UnsupportedOperationException("Cannot findProductById in ProductsFailed instance");
    }

    @Override
    public void decorateProduct(String id, Product product) { }

    @Override
    public void decorateProduct(int key, Product product) { }

    @Override
    public void removeProducts(Class<? extends Product> decorateProduct) { }

    @Override
    public Iterator<Product> iterator() {
        throw new UnsupportedOperationException("Cannot iterator in ProductsFailed instance");
    }

    @Override
    public Object formForSend() {
        return errorMessage;
    }
}
