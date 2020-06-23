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

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.fd.api.service.entity.Description;
import ru.fd.api.service.producer.entity.DescriptionProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RseProductsWithDescriptionImpl implements ResultSetExtractor<Map<String, Description>> {

    private final DescriptionProducer descriptionProducer;

    public RseProductsWithDescriptionImpl(DescriptionProducer descriptionProducer) {
        this.descriptionProducer = descriptionProducer;
    }

    @Override
    public Map<String, Description> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Description> descMap = new HashMap<>();
        while(rs.next()) {
            String id = rs.getString("GIDTovar").trim();
            String shortDescription = rs.getString("short_description").trim();
            String fullDescription = rs.getString("full_description").trim();

            Description description = descriptionProducer
                    .getDescriptionFullInstance(descriptionProducer
                                    .getDescriptionShortInstance(shortDescription),
                            fullDescription);

            descMap.put(id, description);
        }
        return descMap;
    }
}
