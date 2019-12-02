package test.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.producer.entity.ProductProducer;

@Service("productProducerDefault")
public class ProductProducerTestImpl implements ProductProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Product getProductSimpleInstance(
            String id, String categoryId, String vendorId, String unitId, String name,
            short tax, String articul, String code, boolean noReturn) {
        return new ProductSimpleImpl(id, categoryId, vendorId, unitId, name, tax, articul, code, noReturn);
    }

    @Override
    public Product getProductWithAttributesInstance(Product product, Attributes attributes) {
        return new ProductWithAttributesImpl(product, attributes);
    }

    @Override
    public Product getProductWithBalancesInstance(Product product, Balances balances) {
        return new ProductWithBalancesImpl(product, balances);
    }

    @Override
    public Product getProductWithPricesInstance(Product product, Prices prices) {
        return new ProductWithPricesImpl(product, prices);
    }

    @Override
    public Product getProductWithStatusesInstance(Product product, Statuses statuses) {
        return new ProductWithStatusesImpl(product, statuses);
    }

    @Override
    public Product getProductWithShortDescription(Product product, String shortDescription) {
        return new ProductWithShortDescriptionImpl(product, shortDescription);
    }

    @Override
    public Product getProductWithFullDescription(Product product, String fullDescription) {
        return new ProductWithFullDescriptionImpl(product, fullDescription);
    }
}
