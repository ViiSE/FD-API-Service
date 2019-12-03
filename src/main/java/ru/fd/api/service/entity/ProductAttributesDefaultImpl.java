package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductAttributePojo;
import ru.fd.api.service.data.ProductAttributesPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("productAttributesDefault")
@Scope("prototype")
public class ProductAttributesDefaultImpl implements Attributes {

    private final List<ProductAttribute> attributes;

    public ProductAttributesDefaultImpl(List<ProductAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Object formForSend() {
        List<ProductAttributePojo> attributePojos = attributes.stream()
                .map(attribute -> (ProductAttributePojo) attribute.formForSend())
                .collect(Collectors.toList());
        return new ProductAttributesPojo(attributePojos);
    }
}
