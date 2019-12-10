package test.repository.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.processor.*;

import java.util.HashMap;
import java.util.Map;

@Service("productsRepositoryProcessorsSingleton")
@Scope("prototype")
public class ProductsRepositoryProcessorsNotSingletonImpl implements ProductsRepositoryProcessors  {

    private static final Map<String, ProductsRepositoryProcessor> processors = new HashMap<>();

    public ProductsRepositoryProcessorsNotSingletonImpl(
            ProductsRepositoryProducer prodsRepoProducer, ProductProducer prodProducer) {
        processors.put("simple", new ProductsRepositorySimpleProcessorImpl(prodsRepoProducer, null, null));
        processors.put("attributes", new ProductsRepositoryWithAttributesProcessorImpl(prodsRepoProducer, prodProducer, null));
        processors.put("balances", new ProductsRepositoryWithBalancesProcessorImpl(prodsRepoProducer, prodProducer, null, null, null));
        processors.put("prices", new ProductsRepositoryWithPricesProcessorImpl(prodsRepoProducer, prodProducer, null, null, null));
        processors.put("statuses", new ProductsRepositoryWithStatusesProcessorImpl(prodsRepoProducer, prodProducer, null));
        processors.put("shortDescriptions", new ProductsRepositoryWithShortDescriptionProcessorImpl(prodsRepoProducer, prodProducer, null));
        processors.put("fullDescriptions", new ProductsRepositoryWithFullDescriptionProcessorImpl(prodsRepoProducer, prodProducer, null));
    }

    @Override
    public ProductsRepositoryProcessor processor(String key) {
        return processors.get(key);
    }
}
