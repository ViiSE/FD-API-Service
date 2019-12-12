package ru.fd.api.service.producer.repository;

import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.StatusProducer;
import ru.fd.api.service.producer.entity.StatusesProducer;
import ru.fd.api.service.repository.StatusesRepository;

public interface StatusesRepositoryProducer {
    StatusesRepository getStatusesRepositoryDefaultInstance(
            StatusProducer statusProducer,
            StatusesProducer statusesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
}
