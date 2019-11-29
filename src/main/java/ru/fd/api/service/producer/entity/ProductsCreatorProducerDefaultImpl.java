package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.ProductsCreator;

import java.util.List;

@Service("productsCreatorProducerDefault")
public class ProductsCreatorProducerDefaultImpl implements ProductsCreatorProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public ProductsCreator getProductsCreatorDefaultInstance(List<String> with) {
        return (ProductsCreator) ctx.getBean("productsCreatorDefault", with);
    }
}