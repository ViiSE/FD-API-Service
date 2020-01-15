package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ProductAttribute", description = "Атрибут товара")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductAttributePojo {

    @ApiModelProperty(notes = "GID атрибута товара", position = 1)
    private final String attributeId;
    @ApiModelProperty(notes = "Значение атрибута", position = 2)
    private final String value;

    @JsonCreator
    public ProductAttributePojo(
            @JsonProperty("attribute_id") String attributeId,
            @JsonProperty("value") String value) {
        this.attributeId = attributeId;
        this.value = value;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public String getValue() {
        return value;
    }
}
