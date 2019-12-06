package ru.fd.api.service.producer.creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.AttributesCreator;
import ru.fd.api.service.repository.AttributesRepository;

@Service("attributesCreatorProducerDefault")
public class AttributesCreatorProducerDefaultImpl implements AttributesCreatorProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public AttributesCreator getAttributesCreatorDefaultInstance(AttributesRepository attributesRepository) {
        return (AttributesCreator) ctx.getBean("attributesCreatorDefault", attributesRepository);
    }
}