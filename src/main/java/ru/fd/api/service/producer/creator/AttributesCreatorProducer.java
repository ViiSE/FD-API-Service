package ru.fd.api.service.producer.creator;

import ru.fd.api.service.creator.AttributesCreator;
import ru.fd.api.service.repository.AttributesRepository;

public interface AttributesCreatorProducer {
    AttributesCreator getAttributesCreatorDefaultInstance(AttributesRepository attributesRepository);
}
