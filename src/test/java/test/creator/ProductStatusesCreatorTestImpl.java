package test.creator;

import ru.fd.api.service.entity.Status;
import ru.fd.api.service.entity.ProductStatusImpl;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.entity.ProductStatusesImpl;

import java.util.ArrayList;

public class ProductStatusesCreatorTestImpl implements StatusesCreator {

    @Override
    public Statuses createStatuses() {
        Status status1 = new ProductStatusImpl("dep_111", "status_111");
        Status status2 = new ProductStatusImpl("dep_222", "status_222");
        return new ProductStatusesImpl(new ArrayList<>() {{ add(status1); add(status2); }});
    }
}