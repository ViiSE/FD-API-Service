package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "Attribute")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AttributePojo {

    private final String id;
    private final String groupId;
    private final String name;

    @JsonCreator
    public AttributePojo(
            @JsonProperty("id") String id,
            @JsonProperty("group_id") String groupId,
            @JsonProperty("name") String name) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }
}
