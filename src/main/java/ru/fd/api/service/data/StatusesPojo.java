package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel("Statuses")
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
