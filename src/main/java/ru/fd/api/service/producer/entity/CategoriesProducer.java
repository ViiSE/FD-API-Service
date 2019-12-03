package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.Balance;
import ru.fd.api.service.entity.Balances;
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.entity.Category;

import java.util.List;

public interface CategoriesProducer {
    Categories getCategoriesDefaultInstance(List<Category> categories);
}
