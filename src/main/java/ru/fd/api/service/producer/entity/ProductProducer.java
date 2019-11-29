package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.*;

public interface ProductProducer {
    Product getProductSimpleInstance(
            String id, String categoryId, String vendorId, String unitId, String name,
            short tax, String articul, String code, boolean noReturn);
    Product getProductWithAttributesInstance(Product product, Attributes attributes);
    Product getProductWithBalancesInstance(Product product, Balances balances);
    Product getProductWithPricesInstance(Product product, Prices prices);
    Product getProductWithStatusesInstance(Product product, Statuses statuses);
    Product getProductWithShortDescription(Product product, String shortDescription);
    Product getProductWithFullDescription(Product product, String fullDescription);
}
