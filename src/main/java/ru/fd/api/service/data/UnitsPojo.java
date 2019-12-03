package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UnitsPojo {

    private final List<UnitPojo> units;

    @JsonCreator
    public UnitsPojo(List<UnitPojo> units) {
        this.units = units;
    }

    public List<UnitPojo> getUnits() {
        return units;
    }
}
