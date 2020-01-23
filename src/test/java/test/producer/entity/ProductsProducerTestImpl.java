/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.producer.entity;

import ru.fd.api.service.entity.*;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;

import java.util.List;

public class ProductsProducerTestImpl implements ProductsProducer {

    @Override
    public Products getOrderProductsDefaultInstance(List<Product> products) {
        return new OrderProductsDefaultImpl(products);
    }

    @Override
    public Products getProductsDefaultInstance(ProductProducer productProducer, List<Product> products) {
        return new ProductsDefaultImpl(productProducer, products);
    }

    @Override
    public Products getProductsFailedInstance(String errorMessage) {
        return new ProductsFailedImpl(errorMessage);
    }
}
