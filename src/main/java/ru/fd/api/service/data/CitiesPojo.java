/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "Cities", description = "Города доставки")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CitiesPojo {

    @ApiModelProperty(notes = "Список городов доставки", position = 1)
    private final List<CityPojo> cities;

    @JsonCreator
    public CitiesPojo(@JsonProperty("cities") List<CityPojo> cities) {
        this.cities = cities;
    }

    public List<CityPojo> getCities() {
        return cities;
    }
}
