package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductAttributesPojo;
import ru.fd.api.service.data.ProductPojo;

@Component("productWithAttributes")
@Scope("prototype")
public class ProductWithAttributesImpl implements Product {

    private final Product product;
    private final Attributes attributes;

    public ProductWithAttributesImpl(Product product, Attributes attributes) {
        this.product = product;
        this.attributes = attributes;
    }

    @Override
    public String id() {
        return product.id();
    }

    @Override
    public Object formForSend() {
        ProductAttributesPojo attributesPojo = (ProductAttributesPojo) attributes.formForSend();
        ProductPojo productPojo = (ProductPojo) product.formForSend();
        productPojo.setAttributes(attributesPojo);
        return productPojo;
    }
}
