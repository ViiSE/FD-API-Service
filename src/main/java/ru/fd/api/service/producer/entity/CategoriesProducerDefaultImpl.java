package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Categories;
import ru.fd.api.service.entity.Category;

import java.util.List;

@Service("categoriesProducerDefault")
public class CategoriesProducerDefaultImpl implements CategoriesProducer {

    private final ApplicationContext ctx;

    public CategoriesProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Categories getCategoriesDefaultInstance(List<Category> categories) {
        return (Categories) ctx.getBean("categoriesDefault", categories);
    }
}
