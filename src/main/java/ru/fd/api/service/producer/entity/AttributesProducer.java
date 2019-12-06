package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.Attribute;
import ru.fd.api.service.entity.Attributes;

import java.util.List;

public interface AttributesProducer {
    Attributes getAttributesDefaultInstance(List<Attribute> attributes);
}
