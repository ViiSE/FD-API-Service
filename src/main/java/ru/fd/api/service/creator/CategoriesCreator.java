package ru.fd.api.service.creator;

import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.exception.CreatorException;

public interface CategoriesCreator {
    Categories create() throws CreatorException;
}
