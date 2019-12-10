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
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.CategoriesProducer;
import ru.fd.api.service.producer.entity.CategoryProducer;
import ru.fd.api.service.repository.mapper.CategoriesDefaultRowMapper;

@Repository("categoriesRepositoryDefault")
@Scope("prototype")
public class CategoriesRepositoryDefaultImpl implements CategoriesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final CategoryProducer categoryProducer;
    private final CategoriesProducer categoriesProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public CategoriesRepositoryDefaultImpl(
            CategoryProducer categoryProducer,
            CategoriesProducer categoriesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.categoryProducer = categoryProducer;
        this.categoriesProducer = categoriesProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Categories readCategories() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("categories.sql").content(),
                    new CategoriesDefaultRowMapper(categoryProducer, categoriesProducer));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
