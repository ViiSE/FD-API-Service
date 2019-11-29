package ru.fd.api.service.repository.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.ProductsRepository;

@Component("productsRepositorySimpleProcessor")
@Scope("prototype")
public class ProductsRepositorySimpleProcessorImpl implements ProductsRepositoryProcessor {

    private final ProductsRepositoryProducer prodsRepoProducer;

    public ProductsRepositorySimpleProcessorImpl(ProductsRepositoryProducer prodsRepoProducer) {
        this.prodsRepoProducer = prodsRepoProducer;
    }

    @Override
    public ProductsRepository apply(ProductsRepository productsRepository) {
        return prodsRepoProducer.getProductsRepositorySimpleInstance();
    }
}
