package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ProductStatus", description = "Статус товара")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductStatusPojo {

    @ApiModelProperty(notes = "GID подразделения", position = 1)
    private final String departmentId;
    @ApiModelProperty(notes = "GID статуса", position = 2)
    private final String statusId;

    @JsonCreator
    public ProductStatusPojo(
            @JsonProperty("department_id") String departmentId,
            @JsonProperty("status_id") String statusId) {
        this.departmentId = departmentId;
        this.statusId = statusId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getStatusId() {
        return statusId;
    }
}
