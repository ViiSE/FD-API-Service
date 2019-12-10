package ru.fd.api.service.producer.repository;

import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.database.SQLReader;
import ru.fd.api.service.producer.database.SQLQueryProducer;
import ru.fd.api.service.producer.database.SQLReaderProducer;
import ru.fd.api.service.producer.entity.UnitProducer;
import ru.fd.api.service.producer.entity.UnitsProducer;
import ru.fd.api.service.repository.UnitsRepository;

public interface UnitsRepositoryProducer {
    UnitsRepository getUnitsRepositoryDefaultInstance(
            UnitProducer unitProducer,
            UnitsProducer unitsProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
}
