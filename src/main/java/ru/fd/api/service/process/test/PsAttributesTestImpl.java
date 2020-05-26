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

import ru.fd.api.service.entity.Attribute;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.entity.ProductAttributeImpl;
import ru.fd.api.service.entity.ProductAttributesImpl;
import ru.fd.api.service.process.Process;

import java.util.ArrayList;

public class PsAttributesTestImpl implements Process<Attributes, Void> {

    @Override
    public Attributes answer(Void v) {
        Attribute attribute1 = new ProductAttributeImpl("attr_1", "value attr 1");
        Attribute attribute2 = new ProductAttributeImpl("attr_2", "value attr 2");
        return new ProductAttributesImpl(new ArrayList<>() {{ add(attribute1); add(attribute2); }});
    }
}
