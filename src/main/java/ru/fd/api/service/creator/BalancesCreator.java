package ru.fd.api.service.creator;

import ru.fd.api.service.entity.Balances;
import ru.fd.api.service.exception.CreatorException;

public interface BalancesCreator {
    Balances create() throws CreatorException;
}
