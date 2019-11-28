package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StatusesPojo {

    private final List<StatusPojo> statuses;

    @JsonCreator
    public StatusesPojo(List<StatusPojo> statuses) {
        this.statuses = statuses;
    }

    public List<StatusPojo> getStatuses() {
        return statuses;
    }
}
