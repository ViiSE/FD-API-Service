package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.DepartmentProducer;
import ru.fd.api.service.repository.mapper.DepartmentsDefaultRowMapper;

@Repository("departmentsRepositoryDefault")
public class DepartmentsRepositoryDefaultImpl implements DepartmentsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DepartmentProducer departmentProducer;

    @Override
    public Departments readDepartments() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject("SQL HERE", new DepartmentsDefaultRowMapper(departmentProducer));
        } catch (DataAccessException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
