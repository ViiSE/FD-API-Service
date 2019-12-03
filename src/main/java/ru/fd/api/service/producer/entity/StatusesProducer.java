package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.Status;
import ru.fd.api.service.entity.Statuses;

import java.util.List;

public interface StatusesProducer {
    Statuses getStatusesDefaultInstance(List<Status> statuses);
}
