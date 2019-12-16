package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiModel(value = "AttributeGroups")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AttributeGroupsPojo {

    private final List<AttributeGroupPojo> attributeGroups;

    @JsonCreator
    public AttributeGroupsPojo(@JsonProperty("attribute_groups") List<AttributeGroupPojo> attributeGroups){
        this.attributeGroups = attributeGroups;
    }

    public List<AttributeGroupPojo> getAttributeGroups() {
        return attributeGroups;
    }
}
