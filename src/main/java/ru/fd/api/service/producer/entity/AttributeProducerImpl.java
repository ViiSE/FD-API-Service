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

package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Attribute;

@Service("attributeProducerImpl")
public class AttributeProducerImpl implements AttributeProducer {

    private final ApplicationContext ctx;

    public AttributeProducerImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Attribute getAttributeInstance(String id, long groupId, String name) {
        return (Attribute) ctx.getBean("attribute", id, groupId, name);
    }

    @Override
    public Attribute getAttributeWithCodeAvardaInstance(Attribute attribute, String codeAvarda) {
        return (Attribute) ctx.getBean("attributeWithCodeAvarda", attribute, codeAvarda);
    }

    @Override
    public Attribute getProductAttributeInstance(String id, String value) {
        return (Attribute) ctx.getBean("productAttribute", id, value);
    }

    @Override
    public Attribute getProductAttributeWithCodeAvardaInstance(Attribute attribute, String codeAvarda) {
        return (Attribute) ctx.getBean("productAttributeWithCodeAvarda", attribute, codeAvarda);
    }
}
