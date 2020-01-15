package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "ProductStatuses", description = "Статусы товара")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductStatusesPojo {

    @ApiModelProperty(notes = "Список статусов товара", position = 1)
    private final List<ProductStatusPojo> statuses;

    @JsonCreator
    public ProductStatusesPojo(List<ProductStatusPojo> statuses) {
        this.statuses = statuses;
    }

    public List<ProductStatusPojo> getStatuses() {
        return statuses;
    }
}
