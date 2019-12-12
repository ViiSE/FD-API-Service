package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductPojo;

@Component("productWithShortDescription")
@Scope("prototype")
public class ProductWithShortDescriptionImpl implements Product {

    private final Product product;
    private final String shortDescription;

    public ProductWithShortDescriptionImpl(Product product, String shortDescription) {
        this.product = product;
        this.shortDescription = shortDescription;
    }

    @Override
    public String id() {
        return product.id();
    }

    @Override
    public int key() {
        return product.key();
    }

    @Override
    public Object formForSend() {
        ProductPojo productPojo = (ProductPojo) product.formForSend();
        productPojo.setShortDescription(shortDescription);
        return productPojo;
    }
}
