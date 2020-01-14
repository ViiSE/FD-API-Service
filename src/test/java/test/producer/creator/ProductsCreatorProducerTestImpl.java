/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.producer.creator;

import ru.fd.api.service.creator.OrderProductsCreatorDefaultImpl;
import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.producer.creator.ProductsCreatorProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessors;

import java.util.List;

public class ProductsCreatorProducerTestImpl implements ProductsCreatorProducer {

    @Override
    public ProductsCreator getProductsCreatorDefaultInstance(ProductsRepositoryProcessors prodsReposPrc, List<String> with) {
        // TODO: 09.01.2020 CREATE IMPLEMENTATION
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public ProductsCreator getOrderProductsCreatorDefaultInstance(
            OrderPojo orderPojo,
            ProductsProducer orderProductsProducer,
            ProductProducer productProducer) {
        return new OrderProductsCreatorDefaultImpl(orderPojo, orderProductsProducer, productProducer);
    }
}
