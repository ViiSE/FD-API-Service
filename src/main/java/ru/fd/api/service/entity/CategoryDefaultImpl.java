package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.CategoryPojo;

@Component("categoryDefault")
@Scope("prototype")
public class CategoryDefaultImpl implements Category {

    private final String categoryId;
    private final String name;

    public CategoryDefaultImpl(String categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    @Override
    public Object formForSend() {
        return new CategoryPojo(categoryId, name);
    }
}
