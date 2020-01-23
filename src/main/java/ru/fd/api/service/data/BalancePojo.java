package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "Balance", description = "Остаток товара")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BalancePojo {

    @ApiModelProperty(notes = "GID подразделения", position = 1)
    private final String departmentId;
    @ApiModelProperty(notes = "Количество", position = 2)
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
