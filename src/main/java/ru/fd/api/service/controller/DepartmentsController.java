package ru.fd.api.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fd.api.service.creator.DepartmentsCreator;
import ru.fd.api.service.data.DepartmentsPojo;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;
import ru.fd.api.service.DepartmentsService;
import ru.fd.api.service.SQLQueryCreatorService;

import java.util.ArrayList;

@Controller
public class DepartmentsController {

    @Autowired private DepartmentsService departmentsService;
    @Autowired private SQLQueryCreatorService sqlQueryCreatorService;
    @Autowired private LoggerService logger;


    @GetMapping("/departments")
    @ResponseBody
    public DepartmentsPojo departments() {
        try {
            DepartmentsCreator departmentsCreator = departmentsService.departmentsCreatorProducer()
                    .getDepartmentsCreatorDefaultInstance(
                            departmentsService.departmentsRepositoryProducer()
                                    .getDepartmentsRepositoryDefaultInstance(
                                            departmentsService.departmentProducer(),
                                            departmentsService.departmentsProducer(),
                                            sqlQueryCreatorService.sqlQueryCreatorFromFileString()));
            DepartmentsPojo departmentsPojo = (DepartmentsPojo) departmentsCreator.create().formForSend();
            logger.info(DepartmentsController.class, "Site request departments");
            return departmentsPojo;
        } catch (CreatorException ex) {
            logger.error(DepartmentsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new DepartmentsPojo(new ArrayList<>());
        }
    }
}
