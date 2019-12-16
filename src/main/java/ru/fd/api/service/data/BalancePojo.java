package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;


@ApiModel("Balance")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BalancePojo {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String productId;
    private final String departmentId;
    private final int quantity;

    @JsonCreator
    public BalancePojo(
            @JsonProperty("department_id") String departmentId,
            @JsonProperty("quantity") int quantity) {
        this.departmentId = departmentId;
        this.quantity = quantity;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public int getQuantity() {
        return quantity;
    }
}
