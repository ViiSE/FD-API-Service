package ru.fd.api.service.producer.creator;

import ru.fd.api.service.creator.CategoriesCreator;
import ru.fd.api.service.creator.UnitsCreator;
import ru.fd.api.service.repository.CategoriesRepository;
import ru.fd.api.service.repository.UnitsRepository;

public interface CategoriesCreatorProducer {
    CategoriesCreator getCategoriesCreatorDefaultInstance(CategoriesRepository categoriesRepository);
}
