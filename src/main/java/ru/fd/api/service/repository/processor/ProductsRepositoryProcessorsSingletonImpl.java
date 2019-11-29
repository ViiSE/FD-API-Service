package ru.fd.api.service.repository.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;

import java.util.HashMap;
import java.util.Map;

@Service("productsRepositoryProcessorsSingleton")
@Scope("prototype")
public class ProductsRepositoryProcessorsSingletonImpl implements ProductsRepositoryProcessors  {

    private static final Map<String, ProductsRepositoryProcessor> processors = new HashMap<>();

    public ProductsRepositoryProcessorsSingletonImpl(
            ProductsRepositoryProducer prodsRepoProducer, ProductProducer prodProducer) {
        if(processors.isEmpty()) {
            processors.put("simple", new ProductsRepositorySimpleProcessorImpl(prodsRepoProducer));
            processors.put("attributes", new ProductsRepositoryWithAttributesProcessorImpl(prodsRepoProducer, prodProducer));
            processors.put("balances", new ProductsRepositoryWithBalancesProcessorImpl(prodsRepoProducer, prodProducer));
            processors.put("prices", new ProductsRepositoryWithPricesProcessorImpl(prodsRepoProducer, prodProducer));
            processors.put("statuses", new ProductsRepositoryWithStatusesProcessorImpl(prodsRepoProducer, prodProducer));
            processors.put("shortDescriptions", new ProductsRepositoryWithShortDescriptionProcessorImpl(prodsRepoProducer, prodProducer));
            processors.put("fullDescriptions", new ProductsRepositoryWithFullDescriptionProcessorImpl(prodsRepoProducer, prodProducer));
        }
    }

    @Override
    public ProductsRepositoryProcessor processor(String key) {
        return processors.get(key);
    }
}
