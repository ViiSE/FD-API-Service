package ru.fd.api.service.producer.creator;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.AttributeGroupsCreator;
import ru.fd.api.service.repository.AttributeGroupsRepository;

@Service("attributeGroupsCreatorProducerDefault")
public class AttributeGroupsCreatorProducerDefaultImpl implements AttributeGroupsCreatorProducer {

    private final ApplicationContext ctx;

    public AttributeGroupsCreatorProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public AttributeGroupsCreator getAttributeGroupsCreatorDefaultInstance(AttributeGroupsRepository attributeGroupsRepository) {
        return (AttributeGroupsCreator) ctx.getBean("attributeGroupsCreatorDefault", attributeGroupsRepository);
    }
}