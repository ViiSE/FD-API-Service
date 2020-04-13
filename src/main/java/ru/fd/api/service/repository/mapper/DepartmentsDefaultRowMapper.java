package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Department;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.producer.entity.DepartmentProducer;
import ru.fd.api.service.producer.entity.DepartmentsProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("departmentsDefaultRowMapper")
public class DepartmentsDefaultRowMapper implements RowMapper<Departments> {

    private final DepartmentProducer departmentProducer;
    private final DepartmentsProducer departmentsProducer;

    public DepartmentsDefaultRowMapper(DepartmentProducer departmentProducer, DepartmentsProducer departmentsProducer) {
        this.departmentProducer = departmentProducer;
        this.departmentsProducer = departmentsProducer;
    }

    @Override
    public Departments mapRow(ResultSet rs, int i) throws SQLException {
        List<Department> departments = new ArrayList<>();
        do {
            String id = rs.getString("GID").trim();
            String name = rs.getString("NAME").trim();

            departments.add(departmentProducer.getDepartmentDefaultInstance(id, name));
        } while(rs.next());
        return departmentsProducer.getDepartmentsDefaultInstance(departments);
    }
}
