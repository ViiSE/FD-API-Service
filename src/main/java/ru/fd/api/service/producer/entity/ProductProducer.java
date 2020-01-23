package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.*;

public interface ProductProducer {
    Product getProductDefaultInstance(
            String id, String categoryId, String vendorId, String unitId, String name,
            short tax, String articul, String code);
    Product getProductSimpleInstance(Product product, int key);
    Product getProductWithAttributesInstance(Product product, Attributes attributes);
    Product getProductWithBalancesInstance(Product product, Balances balances);
    Product getProductWithPricesInstance(Product product, Prices prices);
    Product getProductWithStatusesInstance(Product product, Statuses statuses);
    Product getProductWithShortDescriptionInstance(Product product, String shortDescription);
    Product getProductWithFullDescriptionInstance(Product product, String fullDescription);
    Product getProductWithChangedBalancesInstance(String id, Balances balances);
    Product getOrderProductSimpleInstance(String id, int quantity);
}
