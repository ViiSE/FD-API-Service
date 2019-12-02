package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.DepartmentPojo;
import ru.fd.api.service.data.DepartmentsPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("departmentsDefault")
@Scope("prototype")
public class DepartmentsDefaultImpl implements Departments {

    private final List<Department> departments;

    public DepartmentsDefaultImpl(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public Object formForSend() {
        List<DepartmentPojo> departmentPojos = departments.stream()
                .map(department -> (DepartmentPojo) department.formForSend())
                .collect(Collectors.toList());
        return new DepartmentsPojo(departmentPojos);
    }
}
