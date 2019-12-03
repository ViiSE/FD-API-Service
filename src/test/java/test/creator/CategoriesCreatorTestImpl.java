package test.creator;

import ru.fd.api.service.creator.CategoriesCreator;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.CreatorException;

import java.util.ArrayList;

public class CategoriesCreatorTestImpl implements CategoriesCreator {

    @Override
    public Categories create() throws CreatorException {
        Category category1 = new CategoryDefaultImpl("cat_1", "Category 1");
        Category category2 = new CategoryDefaultImpl("cat_2", "Category 2");
        return new CategoriesDefaultImpl(new ArrayList<>() {{ add(category1); add(category2); }});

    }
}