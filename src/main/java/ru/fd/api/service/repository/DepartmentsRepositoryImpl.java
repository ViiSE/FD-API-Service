/*
 * Copyright 2019 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Department;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.DepartmentProducer;
import ru.fd.api.service.producer.entity.DepartmentsProducer;
import ru.fd.api.service.repository.mapper.RmDepartmentsImpl;

import java.util.List;

@Repository("departmentsRepository")
public class DepartmentsRepositoryImpl implements DepartmentsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final DepartmentProducer depProd;
    private final DepartmentsProducer depsProd;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public DepartmentsRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            DepartmentProducer depProd,
            DepartmentsProducer depsProd,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.jdbcTemplate = jdbcTemplate;
        this.depProd = depProd;
        this.depsProd = depsProd;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Departments read() throws RepositoryException {
        try {
            List<Department> departments = jdbcTemplate.query(
                    sqlQueryCreator.create("departments.sql")
                            .content(),
                    new RmDepartmentsImpl(depProd));
            return depsProd.getDepartmentsInstance(departments);
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex);
        }
    }
}
