package test.repository;

import ru.fd.api.service.creator.AttributeGroupsCreator;
import ru.fd.api.service.creator.AttributeGroupsCreatorDefaultImpl;
import ru.fd.api.service.creator.ProductsCreator;
import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.AttributeGroupsRepository;
import ru.fd.api.service.repository.ProductsRepository;
import test.creator.AttributeGroupsCreatorTestImpl;
import test.creator.ProductsCreatorTestImpl;

public class AttributeGroupsRepositoryTestImpl implements AttributeGroupsRepository {

    @Override
    public AttributeGroups readAttributeGroups() throws RepositoryException {
        AttributeGroupsCreator attrGrCr = new AttributeGroupsCreatorTestImpl();
        try {
            return attrGrCr.create();
        } catch (CreatorException ignore) {}
        return null;
    }
}
