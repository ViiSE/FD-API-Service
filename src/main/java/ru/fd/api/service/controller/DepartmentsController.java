package ru.fd.api.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fd.api.service.creator.DepartmentsCreator;
import ru.fd.api.service.data.DepartmentsPojo;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;

import java.util.ArrayList;

@Api(tags="Departments Controller", description = "Контроллер точек для работы с подразделениями", authorizations = {@Authorization(value = "Bearer")})
@RestController
public class DepartmentsController {

    private final DepartmentsCreator departmentsCreator;
    private final LoggerService logger;

    public DepartmentsController(DepartmentsCreator departmentsCreator, LoggerService logger) {
        this.departmentsCreator = departmentsCreator;
        this.logger = logger;
    }

    @ApiOperation(value = "Выгружает все доступные подразделения")
    @GetMapping("/departments")
    public DepartmentsPojo departments() {
        try {
            DepartmentsPojo departmentsPojo = (DepartmentsPojo) departmentsCreator.create().formForSend();
            logger.info(DepartmentsController.class, "Site request departments");
            return departmentsPojo;
        } catch (CreatorException ex) {
            logger.error(DepartmentsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new DepartmentsPojo(new ArrayList<>());
        }
    }
}
