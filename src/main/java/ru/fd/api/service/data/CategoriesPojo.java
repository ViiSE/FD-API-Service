package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel("Categories")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CategoriesPojo {

    private final List<CategoryPojo> categories;

    @JsonCreator
    public CategoriesPojo(@JsonProperty("categories") List<CategoryPojo> categories) {
        this.categories = categories;
    }

    public List<CategoryPojo> getCategories() {
        return categories;
    }
}
