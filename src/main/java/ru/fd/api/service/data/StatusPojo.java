package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StatusPojo {

    private final String departmentId;
    private final String statusId;

    @JsonCreator
    public StatusPojo(
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
