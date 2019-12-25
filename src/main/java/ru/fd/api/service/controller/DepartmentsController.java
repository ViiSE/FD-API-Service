package ru.fd.api.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fd.api.service.DepartmentsService;
import ru.fd.api.service.SQLQueryCreatorService;
import ru.fd.api.service.creator.DepartmentsCreator;
import ru.fd.api.service.data.DepartmentsPojo;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;

import java.util.ArrayList;

@Api(tags="Departments Controller", description = "Контроллер точек для работы с подразделениями", authorizations = {@Authorization(value = "Bearer")})
@Controller
public class DepartmentsController {

    private final DepartmentsService departmentsService;

    private final SQLQueryCreatorService sqlQueryCreatorService;
    private final LoggerService logger;

    public DepartmentsController(
            DepartmentsService departmentsService,
            SQLQueryCreatorService sqlQueryCreatorService,
            LoggerService logger) {
        this.departmentsService = departmentsService;
        this.sqlQueryCreatorService = sqlQueryCreatorService;
        this.logger = logger;
    }

    @ApiOperation(value = "Выгружает все доступные подразделения")
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
