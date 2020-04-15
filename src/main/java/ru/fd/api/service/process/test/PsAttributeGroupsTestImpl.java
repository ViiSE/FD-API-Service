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

import ru.fd.api.service.entity.AttributeGroup;
import ru.fd.api.service.entity.AttributeGroupImpl;
import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.entity.AttributeGroupsImpl;
import ru.fd.api.service.process.Process;

import java.util.ArrayList;

public class PsAttributeGroupsTestImpl implements Process<AttributeGroups, Void> {

    @Override
    public AttributeGroups answer(Void v) {
        AttributeGroup attGr1 = new AttributeGroupImpl("attr_gr_1", "Attribute group 1");
        AttributeGroup attGr2 = new AttributeGroupImpl("attr_gr_2", "Attribute group 2");
        return new AttributeGroupsImpl(new ArrayList<>() {{ add(attGr1); add(attGr2); }});
    }
}
