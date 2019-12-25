package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Department;
import ru.fd.api.service.entity.Departments;

import java.util.List;

@Service("departmentsProducerDefault")
public class DepartmentsProducerDefaultImpl implements DepartmentsProducer {

    private final ApplicationContext ctx;

    public DepartmentsProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Departments getDepartmentsDefaultInstance(List<Department> departments) {
        return (Departments) ctx.getBean("departmentsDefault", departments);
    }
}
