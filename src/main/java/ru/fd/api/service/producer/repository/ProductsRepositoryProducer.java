package ru.fd.api.service.producer.repository;

import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.repository.ProductsRepository;

public interface ProductsRepositoryProducer {
    ProductsRepository getProductsRepositorySimpleInstance(
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
    ProductsRepository getProductsRepositoryWithAttributesInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
    ProductsRepository getProductsRepositoryWithBalancesInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
    ProductsRepository getProductsRepositoryWithPricesInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            PriceProducer priceProducer,
            PricesProducer pricesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
    ProductsRepository getProductsRepositoryWithStatusesInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
    ProductsRepository getProductsRepositoryWithShortDescriptionInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
    ProductsRepository getProductsRepositoryWithFullDescriptionInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
    ProductsRepository getProductsRepositoryWithChangedBalancesInstance(
            ProductProducer productProducer,
            ProductsProducer productsProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
    ProductsRepository getProductsRepositoryWithChangedBalancesAndOrderInstance(
            Order order,
            ProductProducer productProducer,
            ProductsProducer productsProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
}
