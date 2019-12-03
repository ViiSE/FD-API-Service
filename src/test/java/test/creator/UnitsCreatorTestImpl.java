package test.creator;

import ru.fd.api.service.creator.CategoriesCreator;
import ru.fd.api.service.creator.UnitsCreator;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.CreatorException;

import java.util.ArrayList;

public class UnitsCreatorTestImpl implements UnitsCreator {

    @Override
    public Units create() throws CreatorException {
        Unit unit1 = new UnitDefaultImpl("uni_1", "Unit 1");
        Unit unit2 = new UnitDefaultImpl("uni_2", "Unit 2");
        return new UnitsDefaultImpl(new ArrayList<>() {{ add(unit1); add(unit2); }});

    }
}
