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
import ru.fd.api.service.entity.Attribute;
import ru.fd.api.service.entity.Attributes;
import ru.fd.api.service.producer.entity.AttributeProducer;
import ru.fd.api.service.producer.entity.AttributesProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RseProductsWithAttributesImpl implements ResultSetExtractor<Map<String, Attributes>> {

    private final AttributeProducer attrProd;
    private final AttributesProducer attrsProd;

    public RseProductsWithAttributesImpl(AttributeProducer attrProd, AttributesProducer attrsProd) {
        this.attrProd = attrProd;
        this.attrsProd = attrsProd;
    }

    @Override
    public Map<String, Attributes> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Attributes> attrsMap = new HashMap<>();
        String id = "";
        List<Attribute> attributes = new ArrayList<>();
        while(rs.next()) {
            if(id.isEmpty())
                id = rs.getString("GID_TOVAR").trim();
            if(!id.equals(rs.getString("GID_TOVAR"))) {
                attrsMap.put(id, attrsProd.getAttributesInstance(new ArrayList<>(attributes)));
                id = rs.getString("GID_TOVAR").trim();
                attributes.clear();
            }

            String attr_id = rs.getString("GID_ATTR").trim();
            String value = rs.getString("ATTR_VALUE").trim();
            String codeAvarda = rs.getString("CODE_AVARDA").trim();
            attributes.add(attrProd.getProductAttributeWithCodeAvardaInstance(
                    attrProd.getProductAttributeInstance(attr_id, value),
                    codeAvarda));
        }

        attrsMap.put(id, attrsProd.getProductsAttributesInstance(new ArrayList<>(attributes)));

        return attrsMap;
    }
}
