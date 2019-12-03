package test.creator;

import ru.fd.api.service.creator.CategoriesCreator;
import ru.fd.api.service.creator.StatusesCreator;
import ru.fd.api.service.entity.*;
import ru.fd.api.service.exception.CreatorException;

import java.util.ArrayList;

public class StatusesCreatorTestImpl implements StatusesCreator {

    @Override
    public Statuses create() throws CreatorException {
        Status status1 = new StatusDefaultImpl("stat_1", "Status 1");
        Status status2 = new StatusDefaultImpl("stat_2", "Status 2");
        return new StatusesDefaultImpl(new ArrayList<>() {{ add(status1); add(status2); }});
    }
}