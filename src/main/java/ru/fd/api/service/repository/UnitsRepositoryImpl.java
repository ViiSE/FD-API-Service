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
import ru.fd.api.service.entity.Unit;
import ru.fd.api.service.entity.Units;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.UnitProducer;
import ru.fd.api.service.producer.entity.UnitsProducer;
import ru.fd.api.service.repository.mapper.RmUnitsImpl;

import java.util.List;

@Repository("unitsRepository")
public class UnitsRepositoryImpl implements UnitsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UnitProducer uProd;
    private final UnitsProducer usProd;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public UnitsRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            UnitProducer uProd,
            UnitsProducer usProd,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.jdbcTemplate = jdbcTemplate;
        this.uProd = uProd;
        this.usProd = usProd;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Units read() throws RepositoryException {
        try {
            List<Unit> units = jdbcTemplate.query(
                    sqlQueryCreator.create("units.sql").content(),
                    new RmUnitsImpl(uProd));
            return usProd.getUnitsInstance(units);
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex);
        }
    }
}
