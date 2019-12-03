package ru.fd.api.service.producer.creator;

import ru.fd.api.service.creator.DepartmentsCreator;
import ru.fd.api.service.creator.StatusesCreator;
import ru.fd.api.service.repository.DepartmentsRepository;
import ru.fd.api.service.repository.StatusesRepository;

public interface StatusesCreatorProducer {
    StatusesCreator getStatusesCreatorDefaultInstance(StatusesRepository statusesRepository);
}
