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
import ru.fd.api.service.entity.Unit;
import ru.fd.api.service.producer.entity.UnitProducer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RmUnitsImpl implements RowMapper<Unit> {

    private final UnitProducer unitProducer;

    public RmUnitsImpl(UnitProducer unitProducer) {
        this.unitProducer = unitProducer;
    }

    @Override
    public Unit mapRow(ResultSet rs, int i) throws SQLException {
        String id = rs.getString("OKEI").trim();
        String name = rs.getString("CASE").trim();
        if(name.endsWith("."))
                name = name.substring(0, name.length() - 1);
        return unitProducer.getUnitInstance(id, name);
    }
}
