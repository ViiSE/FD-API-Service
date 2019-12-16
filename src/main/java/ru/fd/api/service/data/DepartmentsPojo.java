package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel("Departments")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DepartmentsPojo {

    private final List<DepartmentPojo> departments;

    @JsonCreator
    public DepartmentsPojo(@JsonProperty("departments") List<DepartmentPojo> departments) {
        this.departments = departments;
    }

    public List<DepartmentPojo> getDepartments() {
        return departments;
    }
}
