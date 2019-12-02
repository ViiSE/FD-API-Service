package ru.fd.api.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fd.api.service.creator.DepartmentsCreator;
import ru.fd.api.service.data.DepartmentsPojo;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerServer;
import ru.fd.api.service.producer.creator.DepartmentsCreatorProducer;
import ru.fd.api.service.producer.repository.DepartmentsRepositoryProducer;

import java.util.ArrayList;

@Controller
public class DepartmentsController {

    @Autowired private DepartmentsCreatorProducer departmentsCrProducer;
    @Autowired private DepartmentsRepositoryProducer departmentsRepoProducer;
    @Autowired private LoggerServer logger;


    @GetMapping("/departments")
    @ResponseBody
    public DepartmentsPojo departments() {
        try {
            DepartmentsCreator departmentsCreator = departmentsCrProducer.getDepartmentsCreatorDefaultInstance(
                    departmentsRepoProducer.getDepartmentsRepositoryDefaultInstance());
            DepartmentsPojo departmentsPojo = (DepartmentsPojo) departmentsCreator.create().formForSend();
            logger.info(DepartmentsController.class, "Site request departments");
            return departmentsPojo;
        } catch (CreatorException ex) {
            logger.error(DepartmentsController.class, ex.getMessage() + "\n\t" + ex.getCause());
            return new DepartmentsPojo(new ArrayList<>());
        }
    }
}
