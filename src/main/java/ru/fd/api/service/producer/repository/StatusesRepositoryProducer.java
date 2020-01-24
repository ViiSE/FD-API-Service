package ru.fd.api.service.producer.repository;

import ru.fd.api.service.repository.StatusesRepository;

public interface StatusesRepositoryProducer {
    StatusesRepository getStatusesRepositoryDefaultInstance();
}
