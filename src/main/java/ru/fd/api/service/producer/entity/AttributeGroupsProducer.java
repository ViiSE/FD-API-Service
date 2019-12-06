package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.AttributeGroup;
import ru.fd.api.service.entity.AttributeGroups;

import java.util.List;

public interface AttributeGroupsProducer {
    AttributeGroups getAttributeGroupsDefaultInstance(List<AttributeGroup> attributeGroups);
}
