package ru.fd.api.service.producer.repository;

import ru.fd.api.service.repository.DepartmentsRepository;

public interface DepartmentsRepositoryProducer {
    DepartmentsRepository getDepartmentsRepositoryDefaultInstance();
}
