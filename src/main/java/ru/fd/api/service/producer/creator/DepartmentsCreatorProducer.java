package ru.fd.api.service.producer.creator;

import ru.fd.api.service.creator.DepartmentsCreator;
import ru.fd.api.service.repository.DepartmentsRepository;

public interface DepartmentsCreatorProducer {
    DepartmentsCreator getDepartmentsCreatorDefaultInstance(DepartmentsRepository departmentsRepository);
}
