package test.producer;

import ru.fd.api.service.entity.Status;
import ru.fd.api.service.entity.StatusDefaultImpl;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.entity.StatusesDefaultImpl;

import java.util.ArrayList;

public class StatusesCreatorTestImpl implements StatusesCreator {

    @Override
    public Statuses createStatuses() {
        Status status1 = new StatusDefaultImpl("dep_111", "status_111");
        Status status2 = new StatusDefaultImpl("dep_222", "status_222");
        return new StatusesDefaultImpl(new ArrayList<>() {{ add(status1); add(status2); }});
    }
}
