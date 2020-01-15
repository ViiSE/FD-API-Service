package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "ProductAttributes", description = "Атрибуты товара")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductAttributesPojo {

    @ApiModelProperty(notes = "Список атрибутов товара", position = 1)
    private final List<ProductAttributePojo> attributes;

    @JsonCreator
    public ProductAttributesPojo(@JsonProperty("attributes") List<ProductAttributePojo> attributes) {
        this.attributes = attributes;
    }

    public List<ProductAttributePojo> getAttributes() {
        return attributes;
    }
}
