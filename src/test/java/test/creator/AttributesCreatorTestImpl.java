package test.creator;

import ru.fd.api.service.creator.AttributesCreator;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.CreatorException;

import java.util.ArrayList;

public class AttributesCreatorTestImpl implements AttributesCreator {

    @Override
    public Attributes create() throws CreatorException {
        ProductAttribute attribute1 = new ProductAttributeDefaultImpl("attr_1", "value attr 1");
        ProductAttribute attribute2 = new ProductAttributeDefaultImpl("attr_2", "value attr 2");
        return new ProductAttributesDefaultImpl(new ArrayList<>() {{ add(attribute1); add(attribute2); }});

    }
}
