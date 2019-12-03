package ru.fd.api.service.producer.repository;

import ru.fd.api.service.repository.BalancesRepository;

public interface BalancesRepositoryProducer {
    BalancesRepository getBalancesRepositoryDefaultInstance();
}
