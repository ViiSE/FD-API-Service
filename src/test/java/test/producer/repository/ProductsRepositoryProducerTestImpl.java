package test.producer.repository;

import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.ProductsRepository;
import ru.fd.api.service.repository.ProductsRepositoryWithChangedBalancesImpl;
import test.repository.*;

public class ProductsRepositoryProducerTestImpl implements ProductsRepositoryProducer {

    @Override
    public ProductsRepository getProductsRepositorySimpleInstance(
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return new ProductsRepositorySimpleTestImpl();
    }

    @Override
    public ProductsRepository getProductsRepositoryWithAttributesInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return new ProductsRepositoryWithAttributesTestImpl(productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithBalancesInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return new ProductsRepositoryWithBalancesTestImpl(productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithPricesInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            PriceProducer priceProducer,
            PricesProducer pricesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return new ProductsRepositoryWithPricesTestImpl(productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithStatusesInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return new ProductsRepositoryWithStatusesTestImpl(productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithShortDescriptionInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return new ProductsRepositoryWithShortDescriptionTestImpl(productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithFullDescriptionInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return new ProductsRepositoryWithFullDescriptionTestImpl(productsRepository, productProducer);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithChangedBalancesInstance(
            ProductProducer productProducer,
            ProductsProducer productsProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return new ProductsRepositoryWithChangedBalancesTestImpl();
    }

    @Override
    public ProductsRepository getProductsRepositoryWithChangedBalancesAndOrderInstance(
            Order order,
            ProductProducer productProducer,
            ProductsProducer productsProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return null;
    }
}
