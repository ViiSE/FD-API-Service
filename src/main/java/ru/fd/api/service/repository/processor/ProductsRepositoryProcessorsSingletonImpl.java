package ru.fd.api.service.repository.processor;

import org.springframework.stereotype.Service;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.*;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;

import java.util.HashMap;
import java.util.Map;

@Service("productsRepositoryProcessorsSingleton")
public class ProductsRepositoryProcessorsSingletonImpl implements ProductsRepositoryProcessors  {

    private static final Map<String, ProductsRepositoryProcessor> processors = new HashMap<>();

    public ProductsRepositoryProcessorsSingletonImpl(
            ProductsRepositoryProducer prodsRepoProducer,
            ProductProducer prodProducer,
            SQLQueryCreator<String, String> sqlQueryCreator,
            BalanceProducer balanceProducer,
            BalancesProducer balancesProducer,
            PriceProducer priceProducer,
            PricesProducer pricesProducer) {
        if(processors.isEmpty()) {
            processors.put("simple", new ProductsRepositorySimpleProcessorImpl(
                    prodsRepoProducer,
                    prodProducer,
                    sqlQueryCreator));
            processors.put("attributes", new ProductsRepositoryWithAttributesProcessorImpl(
                    prodsRepoProducer,
                    prodProducer,
                    sqlQueryCreator));
            processors.put("balances", new ProductsRepositoryWithBalancesProcessorImpl(
                    prodsRepoProducer,
                    prodProducer,
                    balanceProducer,
                    balancesProducer,
                    sqlQueryCreator));
            processors.put("prices", new ProductsRepositoryWithPricesProcessorImpl(
                    prodsRepoProducer,
                    prodProducer,
                    priceProducer,
                    pricesProducer,
                    sqlQueryCreator));
            processors.put("statuses", new ProductsRepositoryWithStatusesProcessorImpl(
                    prodsRepoProducer,
                    prodProducer,
                    sqlQueryCreator));
            processors.put("shortDescriptions", new ProductsRepositoryWithShortDescriptionProcessorImpl(
                    prodsRepoProducer,
                    prodProducer,
                    sqlQueryCreator));
            processors.put("fullDescriptions", new ProductsRepositoryWithFullDescriptionProcessorImpl(
                    prodsRepoProducer,
                    prodProducer,
                    sqlQueryCreator));
        }
    }

    @Override
    public ProductsRepositoryProcessor processor(String key) {
        return processors.get(key);
    }
}
