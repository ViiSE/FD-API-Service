package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "Attributes", description = "Атрибуты товаров")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AttributesPojo {

    @ApiModelProperty(notes = "Список атрибутов", position = 1)
    private final List<AttributePojo> attributes;

    @JsonCreator
    public AttributesPojo(@JsonProperty("attributes") List<AttributePojo> attributes) {
        this.attributes = attributes;
    }

    public List<AttributePojo> getAttributes() {
        return attributes;
    }
}
