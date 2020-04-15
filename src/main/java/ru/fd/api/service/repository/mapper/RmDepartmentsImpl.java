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

@Component("rmDepartments")
public class RmDepartmentsImpl implements RowMapper<Departments> {

    private final DepartmentProducer departmentProducer;
    private final DepartmentsProducer departmentsProducer;

    public RmDepartmentsImpl(DepartmentProducer departmentProducer, DepartmentsProducer departmentsProducer) {
        this.departmentProducer = departmentProducer;
        this.departmentsProducer = departmentsProducer;
    }

    @Override
    public Departments mapRow(ResultSet rs, int i) throws SQLException {
        List<Department> departments = new ArrayList<>();
        do {
            String id = rs.getString("GID").trim();
            String name = rs.getString("NAME").trim();

            departments.add(departmentProducer.getDepartmentInstance(id, name));
        } while(rs.next());
        return departmentsProducer.getDepartmentsInstance(departments);
    }
}
