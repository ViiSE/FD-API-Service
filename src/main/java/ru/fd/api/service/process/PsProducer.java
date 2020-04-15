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

package ru.fd.api.service.process;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Sendable;

@Service("psProducer")
public class PsProducer {

    private final ApplicationContext ctx;

    public PsProducer(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public Process<Sendable, Void> psAttrGroupsInstance() {
        return ctx.getBean("psLgAttributeGroups", PsLgAttributeGroupsImpl.class);
    }

    public Process<Sendable, Void> psAttrsInstance() {
        return ctx.getBean("psLgAttributes", PsLgAttributesImpl.class);
    }

    public Process<Sendable, Void> psCategoriesInstance() {
        return ctx.getBean("psLgCategories", PsLgCategoriesImpl.class);
    }

    public Process<Sendable, Void> psDepartmentsInstance() {
        return ctx.getBean("psLgDepartments", PsLgDepartmentsImpl.class);
    }

    public Process<Sendable, Void> psStatusesInstance() {
        return ctx.getBean("psLgStatuses", PsLgStatusesImpl.class);
    }

    public Process<Sendable, Void> psUnitsInstance() {
        return ctx.getBean("psLgUnits", PsLgUnitsImpl.class);
    }
}
