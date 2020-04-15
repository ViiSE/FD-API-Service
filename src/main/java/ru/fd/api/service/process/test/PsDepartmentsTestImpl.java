/*
 * Copyright 2020 ViiSE
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

package ru.fd.api.service.process.test;

import ru.fd.api.service.entity.Department;
import ru.fd.api.service.entity.DepartmentImpl;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.entity.DepartmentsImpl;
import ru.fd.api.service.process.Process;

import java.util.ArrayList;

public class PsDepartmentsTestImpl implements Process<Departments, Void> {

    @Override
    public Departments answer(Void v) {
        Department department1 = new DepartmentImpl("dep_1", "Department 1");
        Department department2 = new DepartmentImpl("dep_2", "Department 2");
        return new DepartmentsImpl(new ArrayList<>() {{ add(department1); add(department2); }});

    }
}
