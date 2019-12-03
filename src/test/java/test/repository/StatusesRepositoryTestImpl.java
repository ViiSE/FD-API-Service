package test.repository;

import ru.fd.api.service.creator.StatusesCreator;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.StatusesRepository;
import test.creator.StatusesCreatorTestImpl;

public class StatusesRepositoryTestImpl implements StatusesRepository {

    @Override
    public Statuses readStatuses() throws RepositoryException {
        StatusesCreator statCr = new StatusesCreatorTestImpl();
        try {
            return statCr.create();
        } catch (CreatorException ignore) {}
        return null;
    }
}
