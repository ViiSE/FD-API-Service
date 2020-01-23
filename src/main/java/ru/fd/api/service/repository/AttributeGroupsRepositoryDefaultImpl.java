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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.AttributeGroupProducer;
import ru.fd.api.service.producer.entity.AttributeGroupsProducer;
import ru.fd.api.service.repository.mapper.AttributeGroupsDefaultRowMapper;

// TODO: 23.01.2020 CREATE SQL
@Repository("attributeGroupsRepositoryDefault")
public class AttributeGroupsRepositoryDefaultImpl implements AttributeGroupsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final AttributeGroupProducer attributeGroupProducer;
    private final AttributeGroupsProducer attributeGroupsProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public AttributeGroupsRepositoryDefaultImpl(
            AttributeGroupProducer attributeGroupProducer,
            AttributeGroupsProducer attributeGroupsProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.attributeGroupProducer = attributeGroupProducer;
        this.attributeGroupsProducer = attributeGroupsProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public AttributeGroups readAttributeGroups() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("attribute_groups.sql").content(),
                    new AttributeGroupsDefaultRowMapper(/*attributeGroupProducer,*/ attributeGroupsProducer));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
