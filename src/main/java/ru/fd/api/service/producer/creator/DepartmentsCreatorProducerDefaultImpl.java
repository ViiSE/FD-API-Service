package ru.fd.api.service.producer.creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.DepartmentsCreator;
import ru.fd.api.service.repository.DepartmentsRepository;

@Service("departmentsCreatorProducerDefault")
public class DepartmentsCreatorProducerDefaultImpl implements DepartmentsCreatorProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public DepartmentsCreator getDepartmentsCreatorDefaultInstance(DepartmentsRepository departmentsRepository) {
        return (DepartmentsCreator) ctx.getBean("departmentsCreatorDefault", departmentsRepository);
    }
}