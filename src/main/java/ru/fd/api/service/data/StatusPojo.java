package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StatusPojo {

    private final String statusId;
    private final String name;

    @JsonCreator
    public StatusPojo(
            @JsonProperty("status_id") String statusId,
            @JsonProperty("status_id") String name) {
        this.statusId = statusId;
        this.name = name;
    }

    public String getStatusId() {
        return statusId;
    }

    public String getName() {
        return name;
    }
}
