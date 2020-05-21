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

package ru.fd.api.service.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Attribute;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.AttributeProducer;
import ru.fd.api.service.producer.entity.AttributesProducer;
import ru.fd.api.service.repository.mapper.RmAttributesImpl;

import java.util.List;

// TODO: 23.01.2020 CREATE SQL
@Repository("attributesRepository")
public class AttributesRepositoryImpl implements AttributesRepository {

    private final JdbcTemplate jdbcTemplate;
    private final AttributesProducer attrsProducer;
    private final AttributeProducer attrProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public AttributesRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            AttributesProducer attrsProducer,
            AttributeProducer attrProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.jdbcTemplate = jdbcTemplate;
        this.attrsProducer = attrsProducer;
        this.attrProducer = attrProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Attributes read() throws RepositoryException {
        try {
            List<Attribute> attributes = jdbcTemplate.query(
                    sqlQueryCreator.create("attributes.sql").content(),
                    new RmAttributesImpl(attrProducer));
            return attrsProducer.getAttributesInstance(attributes);
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex);
        }
    }
}
