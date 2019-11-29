package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductPojo;
import ru.fd.api.service.data.ProductStatusesPojo;

@Component("productWithStatuses")
@Scope("prototype")
public class ProductWithStatusesImpl implements Product {

    private final Product product;
    private final Statuses statuses;

    public ProductWithStatusesImpl(Product product, Statuses statuses) {
        this.product = product;
        this.statuses = statuses;
    }

    @Override
    public String id() {
        return product.id();
    }

    @Override
    public Object formForSend() {
        ProductPojo productPojo = (ProductPojo) product.formForSend();
        productPojo.setStatuses((ProductStatusesPojo)statuses.formForSend());
        return productPojo;
    }
}
