package ru.fd.api.service.repository.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.ProductsRepository;

@Component("productsRepositoryWithFullDescriptionProcessor")
@Scope("prototype")
public class ProductsRepositoryWithFullDescriptionProcessorImpl implements ProductsRepositoryProcessor {

    private final ProductsRepositoryProducer prodsRepoProducer;
    private final ProductProducer prodProducer;

    public ProductsRepositoryWithFullDescriptionProcessorImpl(
            ProductsRepositoryProducer prodsRepoProducer, ProductProducer prodProducer) {
        this.prodsRepoProducer = prodsRepoProducer;
        this.prodProducer = prodProducer;
    }

    @Override
    public ProductsRepository apply(ProductsRepository productsRepository) {
        return prodsRepoProducer.getProductsRepositoryWithFullDescriptionInstance(productsRepository, prodProducer);
    }
}
