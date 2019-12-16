package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel("ProductAttributes")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductAttributesPojo {

    private final List<ProductAttributePojo> attributes;

    @JsonCreator
    public ProductAttributesPojo(@JsonProperty("attributes") List<ProductAttributePojo> attributes) {
        this.attributes = attributes;
    }

    public List<ProductAttributePojo> getAttributes() {
        return attributes;
    }
}
