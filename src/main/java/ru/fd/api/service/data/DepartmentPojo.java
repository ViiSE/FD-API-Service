package ru.fd.api.service.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;

@ApiModel("Department")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DepartmentPojo {

    private final String id;
    private final String name;

    public DepartmentPojo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
