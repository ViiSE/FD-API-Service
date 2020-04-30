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
import ru.fd.api.service.entity.Countries;
import ru.fd.api.service.entity.Country;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.CountriesProducer;
import ru.fd.api.service.producer.entity.CountryProducer;
import ru.fd.api.service.repository.mapper.RmCountriesImpl;

import java.util.List;

@Repository("countriesRepository")
public class CountriesRepositoryImpl implements CountriesRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CountryProducer cProd;
    private final CountriesProducer csProd;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public CountriesRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            CountryProducer cProd,
            CountriesProducer csProd,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.jdbcTemplate = jdbcTemplate;
        this.cProd = cProd;
        this.csProd = csProd;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Countries read() throws RepositoryException {
        try {
            List<Country> countries = jdbcTemplate.query(
                    sqlQueryCreator.create("countries.sql")
                            .content(),
                    new RmCountriesImpl(cProd));
            return csProd.getCountriesInstance(countries);
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex);
        }
    }
}
