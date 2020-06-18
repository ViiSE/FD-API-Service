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
import ru.fd.api.service.entity.Description;
import ru.fd.api.service.producer.entity.DescriptionProducer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RmProductsWithDescriptionImpl implements RowMapper<Description> {

    private final DescriptionProducer descriptionProducer;

    public RmProductsWithDescriptionImpl(DescriptionProducer descriptionProducer) {
        this.descriptionProducer = descriptionProducer;
    }

    @Override
    public Description mapRow(ResultSet resultSet, int i) throws SQLException {
        String id = resultSet.getString("GIDTovar").trim();
        String shortDescription = resultSet.getString("short_description").trim();
        String fullDescription = resultSet.getString("full_description").trim();

        return descriptionProducer.getDescriptionWithProductIdInstance(
                descriptionProducer.getDescriptionFullInstance(
                        descriptionProducer.getDescriptionShortInstance(shortDescription),
                        fullDescription),
                id);
    }
}
