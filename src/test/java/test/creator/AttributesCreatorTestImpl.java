package test.producer;

import ru.fd.api.service.entity.*;

import java.util.ArrayList;

public class AttributesCreatorTestImpl implements AttributesCreator {

    @Override
    public Attributes createAttributes() {
        Attribute attribute1 = new AttributeDefaultImpl("attr_1", "value attr 1");
        Attribute attribute2 = new AttributeDefaultImpl("attr_2", "value attr 2");
        return new AttributesDefaultImpl(new ArrayList<>() {{ add(attribute1); add(attribute2); }});
    }
}
