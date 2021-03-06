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
import ru.fd.api.service.entity.Attribute;
import ru.fd.api.service.producer.entity.AttributeProducer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RmAttributesImpl implements RowMapper<Attribute> {

    private final AttributeProducer attributeProducer;

    public RmAttributesImpl(AttributeProducer attributeProducer) {
        this.attributeProducer = attributeProducer;
    }

    @Override
    public Attribute mapRow(ResultSet rs, int i) throws SQLException {
        String id = rs.getString("GID").trim();
        String name = rs.getString("name").trim();
        long groupId = rs.getLong("grp");
        String codeAvarda = rs.getString("codeAvarda").trim();
        return attributeProducer.getAttributeWithCodeAvardaInstance(
                attributeProducer.getAttributeInstance(id, groupId, name),
                codeAvarda);
    }
}
