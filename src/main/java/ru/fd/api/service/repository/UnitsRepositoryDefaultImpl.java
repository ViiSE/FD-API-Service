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
import ru.fd.api.service.entity.Units;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.ReaderException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.database.SQLQueryProducer;
import ru.fd.api.service.producer.database.SQLReaderProducer;
import ru.fd.api.service.producer.entity.UnitProducer;
import ru.fd.api.service.producer.entity.UnitsProducer;
import ru.fd.api.service.repository.mapper.UnitsDefaultRowMapper;

@Repository("unitsRepositoryDefault")
public class UnitsRepositoryDefaultImpl implements UnitsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final UnitProducer unitProducer;
    private final UnitsProducer unitsProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public UnitsRepositoryDefaultImpl(
            UnitProducer unitProducer,
            UnitsProducer unitsProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.unitProducer = unitProducer;
        this.unitsProducer = unitsProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Units readUnits() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("units.sql").content(),
                    new UnitsDefaultRowMapper(unitProducer, unitsProducer));
//            return jdbcTemplate.queryForObject(
//                    "SELECT DISTINCT EE.NAME, EE.OKEI FROM EDISM E LEFT JOIN EDISM EE on EE.KOD = E.OWNER WHERE E.kod > 0",
//                    new UnitsDefaultRowMapper(unitProducer, unitsProducer));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
