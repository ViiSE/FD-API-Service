package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;

@ApiModel("ProductAttribute")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductAttributePojo {

    private final String attributeId;
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
