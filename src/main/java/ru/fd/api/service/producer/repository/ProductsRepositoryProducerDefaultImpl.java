package ru.fd.api.service.producer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.repository.ProductsRepository;

@Service("productsRepositoryProducerDefault")
public class ProductsRepositoryProducerDefaultImpl implements ProductsRepositoryProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public ProductsRepository getProductsRepositorySimpleInstance(
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return (ProductsRepository) ctx.getBean(
                "productsRepositorySimple",
                productProducer,
                sqlQueryCreator);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithAttributesInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return (ProductsRepository) ctx.getBean(
                "productsRepositoryWithAttributes",
                productsRepository,productProducer,
                sqlQueryCreator);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithBalancesInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return (ProductsRepository) ctx.getBean(
                "productsRepositoryWithBalances",
                productsRepository,
                productProducer,
                balanceProducer,
                balancesProducer,
                sqlQueryCreator);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithPricesInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            PriceProducer priceProducer,
            PricesProducer pricesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return (ProductsRepository) ctx.getBean(
                "productsRepositoryWithPrices",
                productsRepository,
                productProducer,
                priceProducer,
                pricesProducer,
                sqlQueryCreator);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithStatusesInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return (ProductsRepository) ctx.getBean(
                "productsRepositoryWithStatuses",
                productsRepository,
                productProducer,
                sqlQueryCreator);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithShortDescriptionInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return (ProductsRepository) ctx.getBean(
                "productsRepositoryWithShortDescription",
                productsRepository,
                productProducer,
                sqlQueryCreator);
    }

    @Override
    public ProductsRepository getProductsRepositoryWithFullDescriptionInstance(
            ProductsRepository productsRepository,
            ProductProducer productProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        return (ProductsRepository) ctx.getBean(
                "productsRepositoryWithFullDescription",
                productsRepository,
                productProducer,
                sqlQueryCreator);
    }
}
