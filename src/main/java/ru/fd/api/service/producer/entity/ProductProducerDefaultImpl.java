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
            String id, String categoryId, String vendorId, String unitId,
            String name, short tax, String articul, String code) {
        return (Product) ctx.getBean("productSimple", id, categoryId, vendorId, unitId, name, tax, articul, code);
    }

    @Override
    public Product getProductWithAttributesInstance(Product product, Attributes attributes) {
        return (Product) ctx.getBean("productWithAttributes", product, attributes);
    }

    @Override
    public Product getProductWithBalancesInstance(Product product, Balances balances) {
        return (Product) ctx.getBean("productWithBalances", product, balances);
    }

    @Override
    public Product getProductWithPricesInstance(Product product, Prices prices) {
        return (Product) ctx.getBean("productWithPrices", product, prices);
    }

    @Override
    public Product getProductWithStatusesInstance(Product product, Statuses statuses) {
        return (Product) ctx.getBean("productWithStatuses", product, statuses);
    }

    @Override
    public Product getProductWithShortDescriptionInstance(Product product, String shortDescription) {
        return (Product) ctx.getBean("productWithShortDescription", product, shortDescription);
    }

    @Override
    public Product getProductWithFullDescriptionInstance(Product product, String fullDescription) {
        return (Product) ctx.getBean("productWithFullDescription", product, fullDescription);
    }
}
