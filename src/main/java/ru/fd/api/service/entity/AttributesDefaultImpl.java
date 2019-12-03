package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.AttributePojo;
import ru.fd.api.service.data.AttributesPojo;
import ru.fd.api.service.data.ProductAttributePojo;
import ru.fd.api.service.data.ProductAttributesPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("attributesDefault")
@Scope("prototype")
public class AttributesDefaultImpl implements Attributes {

    private final List<Attribute> attributes;

    public AttributesDefaultImpl(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Object formForSend() {
        List<AttributePojo> attributePojos = attributes.stream()
                .map(attribute -> (AttributePojo) attribute.formForSend())
                .collect(Collectors.toList());
        return new AttributesPojo(attributePojos);
    }
}
