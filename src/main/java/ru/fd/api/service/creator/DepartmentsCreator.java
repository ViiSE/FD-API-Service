package ru.fd.api.service.creator;

import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.exception.CreatorException;

public interface DepartmentsCreator {
    Departments create() throws CreatorException;
}
