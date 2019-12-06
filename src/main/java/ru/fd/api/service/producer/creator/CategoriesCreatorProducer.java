package ru.fd.api.service.producer.creator;

import ru.fd.api.service.creator.CategoriesCreator;
import ru.fd.api.service.repository.CategoriesRepository;

public interface CategoriesCreatorProducer {
    CategoriesCreator getCategoriesCreatorDefaultInstance(CategoriesRepository categoriesRepository);
}
