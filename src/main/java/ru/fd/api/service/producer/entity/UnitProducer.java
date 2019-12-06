package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.Unit;

public interface UnitProducer {
    Unit getUnitDefaultInstance(String id, String name);
}
