package ru.fd.api.service.producer.creator;

import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessors;

import java.util.List;

public interface ProductsCreatorProducer {
    ProductsCreator getProductsCreatorDefaultInstance(ProductsRepositoryProcessors prodsReposPrc, List<String> with);
}
