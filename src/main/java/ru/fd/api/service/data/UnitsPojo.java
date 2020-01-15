package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "Units", description = "Единицы измерения")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UnitsPojo {

    @ApiModelProperty(notes = "Список единиц измерений", position = 1)
    private final List<UnitPojo> units;

    @JsonCreator
    public UnitsPojo(List<UnitPojo> units) {
        this.units = units;
    }

    public List<UnitPojo> getUnits() {
        return units;
    }
}
