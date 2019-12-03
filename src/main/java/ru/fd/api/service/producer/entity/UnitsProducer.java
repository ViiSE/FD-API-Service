package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.Status;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.entity.Unit;
import ru.fd.api.service.entity.Units;

import java.util.List;

public interface UnitsProducer {
    Units getUnitsDefaultInstance(List<Unit> unit);
}
