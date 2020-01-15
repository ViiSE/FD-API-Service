package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "Products", description = "Товары")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductsPojo {

    @ApiModelProperty(notes = "Список товаров", position = 1)
    private final List<ProductPojo> products;

    @JsonCreator
    public ProductsPojo(@JsonProperty("products") List<ProductPojo> products) {
        this.products = products;
    }

    public List<ProductPojo> getProducts() {
        return products;
    }
}
