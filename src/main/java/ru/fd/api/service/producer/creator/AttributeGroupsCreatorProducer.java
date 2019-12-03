package ru.fd.api.service.producer.creator;

import ru.fd.api.service.creator.AttributeGroupsCreator;
import ru.fd.api.service.creator.StatusesCreator;
import ru.fd.api.service.repository.AttributeGroupsRepository;
import ru.fd.api.service.repository.StatusesRepository;

public interface AttributeGroupsCreatorProducer {
    AttributeGroupsCreator getAttributeGroupsCreatorDefaultInstance(AttributeGroupsRepository attributeGroupsRepository);
}
