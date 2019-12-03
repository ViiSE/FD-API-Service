package test.repository;

import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.StatusesRepository;

public class StatusesRepositoryExceptionTestImpl implements StatusesRepository {

    @Override
    public Statuses readStatuses() throws RepositoryException {
        throw new RepositoryException("EXCEPTION! :(");
    }
}
