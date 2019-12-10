/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fd.api.service.producer.creator.DepartmentsCreatorProducer;
import ru.fd.api.service.producer.entity.DepartmentProducer;
import ru.fd.api.service.producer.entity.DepartmentsProducer;
import ru.fd.api.service.producer.repository.DepartmentsRepositoryProducer;

@Service("departmentsServiceDefault")
public class DepartmentsServiceDefaultImpl implements DepartmentsService {

    @Autowired private DepartmentsCreatorProducer departmentsCrProducer;
    @Autowired private DepartmentsRepositoryProducer departmentsRepoProducer;
    @Autowired private DepartmentProducer departmentProducer;
    @Autowired private DepartmentsProducer departmentsProducer;

    @Override
    public DepartmentsCreatorProducer departmentsCreatorProducer() {
        return departmentsCrProducer;
    }

    @Override
    public DepartmentsRepositoryProducer departmentsRepositoryProducer() {
        return departmentsRepoProducer;
    }

    @Override
    public DepartmentProducer departmentProducer() {
        return departmentProducer;
    }

    @Override
    public DepartmentsProducer departmentsProducer() {
        return departmentsProducer;
    }
}
