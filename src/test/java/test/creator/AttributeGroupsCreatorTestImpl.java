package test.creator;

import ru.fd.api.service.creator.AttributeGroupsCreator;
import ru.fd.api.service.entity.AttributeGroup;
import ru.fd.api.service.entity.AttributeGroupDefaultImpl;
import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.entity.AttributeGroupsDefaultImpl;

import java.util.ArrayList;

public class AttributeGroupsCreatorTestImpl implements AttributeGroupsCreator {

    @Override
    public AttributeGroups create() {
        AttributeGroup attGr1 = new AttributeGroupDefaultImpl("attr_gr_1", "Attribute group 1");
        AttributeGroup attGr2 = new AttributeGroupDefaultImpl("attr_gr_2", "Attribute group 2");
        return new AttributeGroupsDefaultImpl(new ArrayList<>() {{ add(attGr1); add(attGr2); }});
    }
}
