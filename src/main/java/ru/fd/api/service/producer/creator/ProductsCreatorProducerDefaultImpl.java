package ru.fd.api.service.producer.creator;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.repository.processor.ProductsRepositoryProcessors;

import java.util.List;

@Service("productsCreatorProducerDefault")
public class ProductsCreatorProducerDefaultImpl implements ProductsCreatorProducer {

    private final ApplicationContext ctx;

    public ProductsCreatorProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public ProductsCreator getProductsCreatorDefaultInstance(ProductsRepositoryProcessors prodsReposPrc, List<String> with) {
        return (ProductsCreator) ctx.getBean("productsCreatorDefault", prodsReposPrc, with);
    }

    @Override
    public ProductsCreator getProductsOrderCreatorInstance(OrderPojo orderPojo) {
        return (ProductsCreator) ctx.getBean("productsOrderCreator", orderPojo);
    }
}