package test.creator;

import ru.fd.api.service.creator.AttributeGroupsCreator;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.CreatorException;

import java.util.ArrayList;

public class AttributeGroupsCreatorTestImpl implements AttributeGroupsCreator {

    @Override
    public AttributeGroups create() {
        AttributeGroup attGr1 = new AttributeGroupDefaultImpl("attr_gr_1", "Attribute group 1");
        AttributeGroup attGr2 = new AttributeGroupDefaultImpl("attr_gr_2", "Attribute group 2");
        return new AttributeGroupsDefaultImpl(new ArrayList<>() {{ add(attGr1); add(attGr2); }});
    }
}
