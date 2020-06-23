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

package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Department;

@Service("departmentProducer")
public class DepartmentProducerImpl implements DepartmentProducer {

    private final ApplicationContext ctx;

    public DepartmentProducerImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Department getDepartmentInstance(String departmentId, String name) {
        return (Department) ctx.getBean("department", departmentId, name);
    }

    @Override
    public Department getDepartmentWithIdInstance(String departmentId) {
        return (Department) ctx.getBean("departmentWithId", departmentId);
    }

    @Override
    public Department getDepartmentWithAddressInstance(Department department, String address) {
        return (Department) ctx.getBean("departmentWithAddress", department, address);
    }
}
