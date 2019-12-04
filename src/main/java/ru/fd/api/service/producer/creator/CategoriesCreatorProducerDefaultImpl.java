package ru.fd.api.service.producer.creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.CategoriesCreator;
import ru.fd.api.service.creator.UnitsCreator;
import ru.fd.api.service.repository.CategoriesRepository;
import ru.fd.api.service.repository.UnitsRepository;

@Service("categoriesCreatorProducerDefault")
public class CategoriesCreatorProducerDefaultImpl implements CategoriesCreatorProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public CategoriesCreator getCategoriesCreatorDefaultInstance(CategoriesRepository categoriesRepository) {
        return (CategoriesCreator) ctx.getBean("categoriesCreatorDefault", categoriesRepository);
    }
}