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

package ru.fd.api.service.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.data.OfferPojo;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Department;
import ru.fd.api.service.entity.Offer;
import ru.fd.api.service.entity.Offers;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.DateOfferProducer;
import ru.fd.api.service.producer.entity.DepartmentProducer;
import ru.fd.api.service.producer.entity.OfferProducer;
import ru.fd.api.service.producer.entity.OffersProducer;
import ru.fd.api.service.repository.mapper.RmOfferDepartmentsImpl;
import ru.fd.api.service.repository.mapper.RmOffersImpl;

import java.util.ArrayList;
import java.util.List;

@Repository("offersRepository")
public class OffersRepositoryImpl implements OffersRepository {

    private final JdbcTemplate jdbcTemplate;
    private final OfferProducer offProd;
    private final OffersProducer offsProd;
    private final DepartmentProducer depProd;
    private final DateOfferProducer dateOffProd;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public OffersRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            OfferProducer offProd,
            OffersProducer offsProd,
            DepartmentProducer depProd,
            DateOfferProducer dateOffProd,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.jdbcTemplate = jdbcTemplate;
        this.offProd = offProd;
        this.offsProd = offsProd;
        this.depProd = depProd;
        this.dateOffProd = dateOffProd;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Offers read() throws RepositoryException {
        try {
            List<Offer> rawOffers = jdbcTemplate.query(
                    sqlQueryCreator.create("offers.sql")
                            .content(),
                    new RmOffersImpl(offProd, depProd, dateOffProd));
            List<Offer> offers = new ArrayList<>();
            for(Offer offer: rawOffers) {
                OfferPojo offPojo = ((OfferPojo) offer.formForSend());
                String rawDepsId = offPojo.getRawListDepartmentsId();
                String sql = sqlQueryCreator.create("offer_departments_gid.sql").content();
                sql = sql.replaceAll("#RAW_DEPARTMENTS_LIST#", rawDepsId);
                List<Department> departments = jdbcTemplate.query(
                        sql,
                        new RmOfferDepartmentsImpl(depProd),
                        offPojo.getId());
                offers.add(offProd.getOfferWithDepartmentsInstance(offer, departments));
            }

            return offsProd.getOffersInstance(offers);
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex);
        }
    }
}
