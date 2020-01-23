package ru.fd.api.service.producer.repository.processor;

import ru.fd.api.service.repository.processor.ProductsRepositoryProcessors;

public interface ProductsRepositoryProcessorsProducer {
    ProductsRepositoryProcessors getProductsRepositoryProcessorsSingletonImpl();
    ProductsRepositoryProcessors getProductsChangedBalancesRepositoryProcessorsSingletonImpl();
}
