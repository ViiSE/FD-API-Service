package test.producer.repository;

import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.ProductsRepository;
import test.repository.ProductsRepositoryWithAttributesExceptionTestImpl;

public class ProductsRepositoryProducerExceptionTestImpl implements ProductsRepositoryProducer {

    @Override
    public ProductsRepository getProductsRepositorySimpleInstance(ProductProducer productProducer, SQLQueryCreator<String, String> sqlQueryCreator) {
        return null;
    }

    @Override
    public ProductsRepository getProductsRepositoryWithAttributesInstance(ProductsRepository productsRepository, ProductProducer productProducer, SQLQueryCreator<String, String> sqlQueryCreator) {
        return new ProductsRepositoryWithAttributesExceptionTestImpl();
    }

    @Override
    public ProductsRepository getProductsRepositoryWithBalancesInstance(ProductsRepository productsRepository, ProductProducer productProducer, BalanceProducer balanceProducer, BalancesProducer balancesProducer, SQLQueryCreator<String, String> sqlQueryCreator) {
        return null;
    }

    @Override
    public ProductsRepository getProductsRepositoryWithPricesInstance(ProductsRepository productsRepository, ProductProducer productProducer, PriceProducer priceProducer, PricesProducer pricesProducer, SQLQueryCreator<String, String> sqlQueryCreator) {
        return null;
    }

    @Override
    public ProductsRepository getProductsRepositoryWithStatusesInstance(ProductsRepository productsRepository, ProductProducer productProducer, SQLQueryCreator<String, String> sqlQueryCreator) {
        return null;
    }

    @Override
    public ProductsRepository getProductsRepositoryWithShortDescriptionInstance(ProductsRepository productsRepository, ProductProducer productProducer, SQLQueryCreator<String, String> sqlQueryCreator) {
        return null;
    }

    @Override
    public ProductsRepository getProductsRepositoryWithFullDescriptionInstance(ProductsRepository productsRepository, ProductProducer productProducer, SQLQueryCreator<String, String> sqlQueryCreator) {
        return null;
    }
}
