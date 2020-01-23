package ru.fd.api.service.producer.repository;

import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.repository.BalancesRepository;

public interface BalancesRepositoryProducer {
    BalancesRepository getBalancesRepositoryDefaultInstance();
}
