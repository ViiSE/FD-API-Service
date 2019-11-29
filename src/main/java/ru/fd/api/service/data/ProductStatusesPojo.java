package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductStatusesPojo {

    private final List<ProductStatusPojo> statuses;

    @JsonCreator
    public ProductStatusesPojo(List<ProductStatusPojo> statuses) {
        this.statuses = statuses;
    }

    public List<ProductStatusPojo> getStatuses() {
        return statuses;
    }
}
