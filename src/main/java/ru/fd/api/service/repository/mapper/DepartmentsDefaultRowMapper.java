package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.Department;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.producer.entity.DepartmentProducer;
import ru.fd.api.service.producer.entity.DepartmentsProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        while(rs.next()) {
            String id = rs.getString("GID").trim();
            String name = rs.getString("NAME").trim();

            departments.add(departmentProducer.getDepartmentDefaultInstance(id, name));
        }
        return departmentsProducer.getDepartmentsDefaultInstance(departments);
    }
}
