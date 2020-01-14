package ru.fd.api.service.producer.creator;

import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessors;

import java.util.List;

public interface ProductsCreatorProducer {
    ProductsCreator getProductsCreatorDefaultInstance(ProductsRepositoryProcessors prodsReposPrc, List<String> with);
    ProductsCreator getOrderProductsCreatorDefaultInstance(
            OrderPojo orderPojo,
            ProductsProducer orderProductsProducer,
            ProductProducer productProducer);
}
