package ru.fd.api.service.producer.entity;

import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.entity.Category;
import ru.fd.api.service.entity.Department;
import ru.fd.api.service.entity.Departments;

import java.util.List;

public interface DepartmentsProducer {
    Departments getDepartmentsDefaultInstance(List<Department> departments);
}
