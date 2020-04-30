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
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Vendor;
import ru.fd.api.service.entity.Vendors;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.VendorProducer;
import ru.fd.api.service.producer.entity.VendorsProducer;
import ru.fd.api.service.repository.mapper.RmVendorsImpl;

import java.util.List;

@Repository("vendorsRepository")
public class VendorsRepositoryImpl implements VendorsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final VendorProducer venProd;
    private final VendorsProducer vensProd;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public VendorsRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            VendorProducer venProd,
            VendorsProducer vensProd,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.jdbcTemplate = jdbcTemplate;
        this.venProd = venProd;
        this.vensProd = vensProd;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Vendors read() throws RepositoryException {
        try {
            List<Vendor> vendors = jdbcTemplate.query(
                    sqlQueryCreator.create("vendors.sql")
                            .content(),
                    new RmVendorsImpl(venProd));
            return vensProd.getVendorsInstance(vendors);
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex);
        }
    }
}
