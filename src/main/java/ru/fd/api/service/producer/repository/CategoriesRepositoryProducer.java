package ru.fd.api.service.producer.repository;

import ru.fd.api.service.repository.CategoriesRepository;

public interface CategoriesRepositoryProducer {
    CategoriesRepository getCategoriesRepositoryDefaultInstance();
}
