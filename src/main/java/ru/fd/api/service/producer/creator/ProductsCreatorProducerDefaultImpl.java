package ru.fd.api.service.producer.creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessors;

import java.util.List;

@Service("productsCreatorProducerDefault")
public class ProductsCreatorProducerDefaultImpl implements ProductsCreatorProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public ProductsCreator getProductsCreatorDefaultInstance(ProductsRepositoryProcessors prodsReposPrc, List<String> with) {
        return (ProductsCreator) ctx.getBean("productsCreatorDefault", prodsReposPrc, with);
    }
}