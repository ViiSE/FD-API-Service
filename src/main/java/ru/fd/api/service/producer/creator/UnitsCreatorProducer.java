package ru.fd.api.service.producer.creator;

import ru.fd.api.service.creator.UnitsCreator;
import ru.fd.api.service.repository.UnitsRepository;

public interface UnitsCreatorProducer {
    UnitsCreator getUnitsCreatorDefaultInstance(UnitsRepository unitsRepository);
}
