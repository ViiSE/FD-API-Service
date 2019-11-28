package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.AttributePojo;
import ru.fd.api.service.data.BalancePojo;

@Component("attributeDefault")
@Scope("prototype")
public class AttributeDefaultImpl implements Attribute {

    private final String attributeId;
    private final String value;

    public AttributeDefaultImpl(String attributeId, String value) {
        this.attributeId = attributeId;
        this.value = value;
    }

    @Override
    public Object formForSend() {
        return new AttributePojo(attributeId, value);
    }
}
