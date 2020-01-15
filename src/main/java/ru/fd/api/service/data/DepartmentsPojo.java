package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "Departments", description = "Подразделения")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DepartmentsPojo {

    @ApiModelProperty(notes = "Список подразделений", position = 1)
    private final List<DepartmentPojo> departments;

    @JsonCreator
    public DepartmentsPojo(@JsonProperty("departments") List<DepartmentPojo> departments) {
        this.departments = departments;
    }

    public List<DepartmentPojo> getDepartments() {
        return departments;
    }
}
