package ru.fd.api.service.producer.repository;

import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.DepartmentProducer;
import ru.fd.api.service.producer.entity.DepartmentsProducer;
import ru.fd.api.service.repository.DepartmentsRepository;

public interface DepartmentsRepositoryProducer {
    DepartmentsRepository getDepartmentsRepositoryDefaultInstance(
            DepartmentProducer departmentProducer,
            DepartmentsProducer departmentsProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
}
