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

@ApiModel(value = "City", description = "Город доставки")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CityPojo {

    @ApiModelProperty(notes = "GID города", position = 1)
    private final String cityId;
    @ApiModelProperty(notes = "Название города", position = 2)
    private final String name;

    @JsonCreator
    public CityPojo(
            @JsonProperty("city_id") String cityId,
            @JsonProperty("name") String name) {
        this.cityId = cityId;
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }
}
