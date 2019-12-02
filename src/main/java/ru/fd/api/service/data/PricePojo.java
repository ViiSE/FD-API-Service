package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PricePojo {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String productId;
    private final String departmentId;
    private final float value;

    @JsonCreator
    public PricePojo(
            @JsonProperty("department_id") String departmentId,
            @JsonProperty("value") float value) {
        this.departmentId = departmentId;
        this.value = value;
    }

    public String getProductId() {
        return productId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public float getValue() {
        return value;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
