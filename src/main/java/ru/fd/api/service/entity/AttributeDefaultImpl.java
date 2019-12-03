package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.AttributePojo;
import ru.fd.api.service.data.BalancePojo;

@Component("attributeDefault")
@Scope("prototype")
public class AttributeDefaultImpl implements Attribute {

    private final String attributeId;
    private final String groupId;
    private final String name;

    public AttributeDefaultImpl(String attributeId, String groupId, String name) {
        this.attributeId = attributeId;
        this.groupId = groupId;
        this.name = name;
    }

    @Override
    public Object formForSend() {
        return new AttributePojo(attributeId, groupId, name);
    }
}
