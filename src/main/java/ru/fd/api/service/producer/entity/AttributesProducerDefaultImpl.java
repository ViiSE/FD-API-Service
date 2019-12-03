package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Attribute;
import ru.fd.api.service.entity.Attributes;

import java.util.List;

@Service("attributesProducerDefault")
public class AttributesProducerDefaultImpl implements AttributesProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Attributes getAttributesDefaultInstance(List<Attribute> attributes) {
        return (Attributes) ctx.getBean("attributesDefault", attributes);
    }
}
