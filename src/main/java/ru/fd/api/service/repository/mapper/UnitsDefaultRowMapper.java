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

package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.entity.Unit;
import ru.fd.api.service.entity.Units;
import ru.fd.api.service.producer.entity.StatusesProducer;
import ru.fd.api.service.producer.entity.UnitProducer;
import ru.fd.api.service.producer.entity.UnitsProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitsDefaultRowMapper implements RowMapper<Units> {

    private final UnitProducer unitProducer;
    private final UnitsProducer unitsProducer;

    public UnitsDefaultRowMapper(UnitProducer unitProducer, UnitsProducer unitsProducer) {
        this.unitProducer = unitProducer;
        this.unitsProducer = unitsProducer;
    }

    @Override
    public Units mapRow(ResultSet rs, int i) throws SQLException {
        List<Unit> units = new ArrayList<>();
        do {
            String id = rs.getString("OKEI").trim();
            String name = rs.getString("NAME").trim();
            units.add(unitProducer.getUnitDefaultInstance(id, name));
        } while(rs.next());
        return unitsProducer.getUnitsDefaultInstance(units);
    }
}
