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

package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.AttributePojo;

@Component("attributeWithCodeAvarda")
@Scope("prototype")
public class AttributeWithCodeAvardaImpl implements Attribute {

    private final Attribute attribute;
    private final String codeAvarda;

    public AttributeWithCodeAvardaImpl(Attribute attribute, String codeAvarda) {
        this.attribute = attribute;
        this.codeAvarda = codeAvarda;
    }

    @Override
    public Object formForSend() {
        AttributePojo attributePojo = (AttributePojo) attribute.formForSend();
        attributePojo.setCodeAvarda(codeAvarda);
        return attributePojo;
    }
}
