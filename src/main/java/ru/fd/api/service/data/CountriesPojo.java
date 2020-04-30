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

package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "Countries", description = "Страны")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CountriesPojo {

    @ApiModelProperty(notes = "Список стран", position = 1)
    private final List<CountryPojo> countries;

    @JsonCreator
    public CountriesPojo(@JsonProperty("countries") List<CountryPojo> countries) {
        this.countries = countries;
    }

    public List<CountryPojo> getCountries() {
        return countries;
    }
}
