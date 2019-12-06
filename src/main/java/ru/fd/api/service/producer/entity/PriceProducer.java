package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.Price;

public interface PriceProducer {
    Price getPriceDefaultInstance(String departmentId, float value);
}
