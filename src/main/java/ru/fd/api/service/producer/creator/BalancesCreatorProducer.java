package ru.fd.api.service.producer.creator;

import ru.fd.api.service.creator.BalancesCreator;
import ru.fd.api.service.repository.BalancesRepository;

public interface BalancesCreatorProducer {
    BalancesCreator getBalancesCreatorDefaultInstance(BalancesRepository balancesRepository);
}
