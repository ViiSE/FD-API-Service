package ru.fd.api.service.producer.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.entity.Category;
import ru.fd.api.service.entity.Department;
import ru.fd.api.service.entity.Departments;

import java.util.List;

@Service("departmentsProducerDefault")
public class DepartmentsProducerDefaultImpl implements DepartmentsProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Departments getDepartmentsDefaultInstance(List<Department> departments) {
        return (Departments) ctx.getBean("departmentsDefault", departments);
    }
}
