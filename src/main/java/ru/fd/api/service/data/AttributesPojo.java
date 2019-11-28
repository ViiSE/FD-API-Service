package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AttributesPojo {

    private final List<AttributePojo> attributes;

    @JsonCreator
    public AttributesPojo(@JsonProperty("attributes") List<AttributePojo> attributes) {
        this.attributes = attributes;
    }

    public List<AttributePojo> getAttributes() {
        return attributes;
    }
}
