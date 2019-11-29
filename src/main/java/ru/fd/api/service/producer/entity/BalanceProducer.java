package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.Balance;

public interface BalanceProducer {
    Balance getBalanceDefaultInstance(String departmentId, int quantity);
}
