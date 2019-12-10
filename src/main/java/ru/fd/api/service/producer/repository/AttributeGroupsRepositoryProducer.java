package ru.fd.api.service.producer.repository;

import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.AttributeGroupProducer;
import ru.fd.api.service.producer.entity.AttributeGroupsProducer;
import ru.fd.api.service.repository.AttributeGroupsRepository;

public interface AttributeGroupsRepositoryProducer {
    AttributeGroupsRepository getAttributeGroupsRepositoryDefaultInstance(
            AttributeGroupProducer attributeGroupProducer,
            AttributeGroupsProducer attributeGroupsProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
}
