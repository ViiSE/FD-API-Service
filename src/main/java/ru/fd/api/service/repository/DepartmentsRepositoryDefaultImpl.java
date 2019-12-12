package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.DepartmentProducer;
import ru.fd.api.service.producer.entity.DepartmentsProducer;
import ru.fd.api.service.repository.mapper.DepartmentsDefaultRowMapper;

@Repository("departmentsRepositoryDefault")
public class DepartmentsRepositoryDefaultImpl implements DepartmentsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final DepartmentProducer departmentProducer;
    private final DepartmentsProducer departmentsProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public DepartmentsRepositoryDefaultImpl(
            DepartmentProducer departmentProducer,
            DepartmentsProducer departmentsProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.departmentProducer = departmentProducer;
        this.departmentsProducer = departmentsProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Departments readDepartments() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("departments.sql")
                            .content(),
                    new DepartmentsDefaultRowMapper(departmentProducer, departmentsProducer));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
