package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.AttributeGroup;
import ru.fd.api.service.entity.AttributeGroups;

import java.util.List;

@Service("attributeGroupsProducerDefault")
public class AttributeGroupsProducerDefaultImpl implements AttributeGroupsProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public AttributeGroups getAttributeGroupsDefaultInstance(List<AttributeGroup> attributeGroups) {
        return (AttributeGroups) ctx.getBean("attributeGroupsDefault", attributeGroups);
    }
}
