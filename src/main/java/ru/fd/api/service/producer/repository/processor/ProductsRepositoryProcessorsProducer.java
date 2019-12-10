package ru.fd.api.service.producer.repository.processor;

import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessors;

public interface ProductsRepositoryProcessorsProducer {
    ProductsRepositoryProcessors getProductsRepositoryProcessorsSingletonImpl(
            ProductsRepositoryProducer prodsRepoProducer,
            ProductProducer prodProducer,
            SQLQueryCreator<String, String> sqlQueryCreator,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            PriceProducer priceProducer,
            PricesProducer pricesProducer);
}
