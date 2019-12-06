package ru.fd.api.service.producer.creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.AttributeGroupsCreator;
import ru.fd.api.service.repository.AttributeGroupsRepository;

@Service("attributeGroupsCreatorProducerDefault")
public class AttributeGroupsCreatorProducerDefaultImpl implements AttributeGroupsCreatorProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public AttributeGroupsCreator getAttributeGroupsCreatorDefaultInstance(AttributeGroupsRepository attributeGroupsRepository) {
        return (AttributeGroupsCreator) ctx.getBean("attributeGroupsCreatorDefault", attributeGroupsRepository);
    }
}