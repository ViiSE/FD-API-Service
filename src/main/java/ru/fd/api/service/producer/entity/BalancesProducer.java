package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.Balance;
import ru.fd.api.service.entity.Balances;

import java.util.List;

public interface BalancesProducer {
    Balances getBalancesDefaultInstance(List<Balance> balances);
}
