package ru.fd.api.service.producer.repository;

import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.CategoriesProducer;
import ru.fd.api.service.producer.entity.CategoryProducer;
import ru.fd.api.service.repository.CategoriesRepository;

public interface CategoriesRepositoryProducer {
    CategoriesRepository getCategoriesRepositoryDefaultInstance(
            CategoryProducer categoryProducer,
            CategoriesProducer categoriesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator);
}
