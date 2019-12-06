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
import ru.fd.api.service.entity.AttributeGroups;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.AttributeGroupsProducer;
import ru.fd.api.service.repository.mapper.AttributeGroupsDefaultRowMapper;

@Repository("attributeGroupsRepositoryDefault")
public class AttributeGroupsRepositoryDefaultImpl implements AttributeGroupsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AttributeGroupsProducer attributeGroupsProducer;

    @Override
    public AttributeGroups readAttributeGroups() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject("SQL HERE", new AttributeGroupsDefaultRowMapper(attributeGroupsProducer));
        } catch (DataAccessException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
