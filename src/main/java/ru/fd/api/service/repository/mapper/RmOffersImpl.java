/*
 * Copyright 2020 ViiSE
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
import ru.fd.api.service.entity.Offer;
import ru.fd.api.service.producer.entity.DateOfferProducer;
import ru.fd.api.service.producer.entity.DepartmentProducer;
import ru.fd.api.service.producer.entity.OfferProducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RmOffersImpl implements RowMapper<Offer> {

    private final OfferProducer offProd;
    private final DepartmentProducer depProd;
    private final DateOfferProducer dateOffProd;

    public RmOffersImpl(
            OfferProducer offProd,
            DepartmentProducer depProd,
            DateOfferProducer dateOffProd) {
        this.offProd = offProd;
        this.depProd = depProd;
        this.dateOffProd = dateOffProd;
    }

    @Override
    public Offer mapRow(ResultSet rs, int i) throws SQLException {
        long id = rs.getLong("kod");
        String name = rs.getString("name").trim();
        LocalDateTime startsAt = rs.getTimestamp("DN_action").toLocalDateTime();
        LocalDateTime finishesAt = rs.getTimestamp("DK_action").toLocalDateTime();
        String rawDepartmentsId = rs.getString("depCodes").trim();

        return offProd.getOfferWithRawDepartmentsListIdInstance(
                offProd.getOfferWithDateOfferInstance(
                        offProd.getOfferWithNameInstance(
                                offProd.getOfferSimpleInstance(id),
                                name
                        ),
                        dateOffProd.getDateOfferFinishesAtInstance(
                                dateOffProd.getDateOfferStartsAtInstance(startsAt),
                                finishesAt)
                ),
                rawDepartmentsId);
    }
}
