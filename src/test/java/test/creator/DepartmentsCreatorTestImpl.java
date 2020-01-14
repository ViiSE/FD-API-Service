package test.creator;

import ru.fd.api.service.creator.DepartmentsCreator;
import ru.fd.api.service.entity.Department;
import ru.fd.api.service.entity.DepartmentDefaultImpl;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.entity.DepartmentsDefaultImpl;
import ru.fd.api.service.exception.CreatorException;

import java.util.ArrayList;

public class DepartmentsCreatorTestImpl implements DepartmentsCreator {

    @Override
    public Departments create() {
        Department department1 = new DepartmentDefaultImpl("dep_1", "Department 1");
        Department department2 = new DepartmentDefaultImpl("dep_2", "Department 2");
        return new DepartmentsDefaultImpl(new ArrayList<>() {{ add(department1); add(department2); }});

    }
}
