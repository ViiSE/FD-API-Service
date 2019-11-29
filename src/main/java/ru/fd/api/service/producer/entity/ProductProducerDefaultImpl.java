package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.*;

@Service("productProducerDefault")
public class ProductProducerDefaultImpl implements ProductProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Product getProductSimpleInstance(
            String id, String categoryId, String vendorId, String unitId, String name,
            short tax, String articul, String code, boolean noReturn) {
        return (Product) ctx.getBean("productSimple", id, categoryId, vendorId, unitId, name, tax, articul, code, noReturn);
    }

    @Override
    public Product getProductWithAttributesInstance(Product product, Attributes attributes) {
        return (Product) ctx.getBean("productWithAttributes", attributes);
    }

    @Override
    public Product getProductWithBalancesInstance(Product product, BalancesProducer balances) {
        return (Product) ctx.getBean("productWithBalances", balances);
    }

    @Override
    public Product getProductWithPricesInstance(Product product, Prices prices) {
        return (Product) ctx.getBean("productWithPricces", prices);
    }

    @Override
    public Product getProductWithStatusesInstance(Product product, Statuses statuses) {
        return (Product) ctx.getBean("productWithStatuses", statuses);
    }

    @Override
    public Product getProductWithShortDescription(Product product, String shortDescription) {
        return (Product) ctx.getBean("productWithShortDescription", shortDescription);
    }

    @Override
    public Product getProductWithFullDescription(Product product, String fullDescription) {
        return (Product) ctx.getBean("productWithFullDescription", fullDescription);
    }
}
