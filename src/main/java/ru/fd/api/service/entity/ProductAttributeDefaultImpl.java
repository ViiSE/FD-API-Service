package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductAttributePojo;

@Component("productAttributeDefault")
@Scope("prototype")
public class ProductAttributeDefaultImpl implements ProductAttribute {

    private final String attributeId;
    private final String value;

    public ProductAttributeDefaultImpl(String attributeId, String value) {
        this.attributeId = attributeId;
        this.value = value;
    }

    @Override
    public Object formForSend() {
        return new ProductAttributePojo(attributeId, value);
    }
}
