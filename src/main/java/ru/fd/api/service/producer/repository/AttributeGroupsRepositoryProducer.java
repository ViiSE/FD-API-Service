package ru.fd.api.service.producer.repository;

import ru.fd.api.service.repository.AttributeGroupsRepository;
import ru.fd.api.service.repository.StatusesRepository;

public interface AttributeGroupsRepositoryProducer {
    AttributeGroupsRepository getAttributeGroupsRepositoryDefaultInstance();
}
