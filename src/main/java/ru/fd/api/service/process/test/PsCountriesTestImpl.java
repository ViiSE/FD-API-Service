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

package ru.fd.api.service.process.test;

import ru.fd.api.service.entity.Countries;
import ru.fd.api.service.entity.CountriesImpl;
import ru.fd.api.service.entity.Country;
import ru.fd.api.service.entity.CountryImpl;
import ru.fd.api.service.process.Process;

import java.util.ArrayList;

public class PsCountriesTestImpl implements Process<Countries, Void> {

    @Override
    public Countries answer(Void v) {
        Country c1 = new CountryImpl("c1", "Country 1");
        Country c2 = new CountryImpl("c2", "");
        return new CountriesImpl(new ArrayList<>() {{ add(c1); add(c2); }});

    }
}
