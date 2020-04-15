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

package ru.fd.api.service.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;

// TODO: 23.01.2020 CREATE SQL
@Repository("statusesRepository")
public class StatusesRepositoryImpl implements StatusesRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Statuses> rmStats;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public StatusesRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            RowMapper<Statuses> rmStats,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.jdbcTemplate = jdbcTemplate;
        this.rmStats = rmStats;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Statuses read() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("statuses.sql").content(),
                    rmStats);
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex);
        }
    }
}
