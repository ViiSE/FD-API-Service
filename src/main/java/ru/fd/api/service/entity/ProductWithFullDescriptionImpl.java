package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductPojo;

@Component("productWithFullDescription")
@Scope("prototype")
public class ProductWithFullDescriptionImpl implements Product {

    private final Product product;
    private final String fullDescription;

    public ProductWithFullDescriptionImpl(Product product, String fullDescription) {
        this.product = product;
        this.fullDescription = fullDescription;
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
        productPojo.setFullDescription(fullDescription);
        return productPojo;
    }
}
