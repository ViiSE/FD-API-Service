package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Status", description = "Статус товаров")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StatusPojo {

    @ApiModelProperty(notes = "GID статуса", position = 1)
    private final String id;
    @ApiModelProperty(notes = "Название статуса", position = 2)
    private final String name;

    @JsonCreator
    public StatusPojo(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name) {
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
