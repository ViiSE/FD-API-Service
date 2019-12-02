package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.DepartmentPojo;

@Component("departmentDefault")
@Scope("prototype")
public class DepartmentDefaultImpl implements Department {

    private final String departmentId;
    private final String name;

    public DepartmentDefaultImpl(String departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
    }

    @Override
    public Object formForSend() {
        return new DepartmentPojo(departmentId, name);
    }
}
