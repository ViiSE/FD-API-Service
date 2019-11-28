package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BalancePojo {

    private final String departmentId;
    private final int quantity;

    @JsonCreator
    public BalancePojo(
            @JsonProperty("department_id") String departmentId,
            @JsonProperty("quantity") int quantity) {
        this.departmentId = departmentId;
        this.quantity = quantity;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public int getQuantity() {
        return quantity;
    }
}
