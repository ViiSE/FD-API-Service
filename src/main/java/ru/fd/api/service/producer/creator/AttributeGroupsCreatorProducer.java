package ru.fd.api.service.producer.creator;

import ru.fd.api.service.creator.AttributeGroupsCreator;
import ru.fd.api.service.repository.AttributeGroupsRepository;

public interface AttributeGroupsCreatorProducer {
    AttributeGroupsCreator getAttributeGroupsCreatorDefaultInstance(AttributeGroupsRepository attributeGroupsRepository);
}
