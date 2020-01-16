/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

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
import ru.fd.api.service.data.CitiesPojo;
import ru.fd.api.service.data.CityPojo;
import ru.fd.api.service.data.DepartmentsPojo;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;

import java.util.ArrayList;

@Api(tags="Cities Controller", description = "Контроллер точек для работы с городами", authorizations = {@Authorization(value = "Bearer")})
@Controller
public class CitiesController {

//    private final DepartmentsService departmentsService;
//
//    private final SQLQueryCreatorService sqlQueryCreatorService;
//    private final LoggerService logger;
//
//    public CitiesController(
//            DepartmentsService departmentsService,
//            SQLQueryCreatorService sqlQueryCreatorService,
//            LoggerService logger) {
//        this.departmentsService = departmentsService;
//        this.sqlQueryCreatorService = sqlQueryCreatorService;
//        this.logger = logger;
//    }
//
    @ApiOperation(value = "Выгружает все города доставки")
    @GetMapping("/cities")
    @ResponseBody
    public CitiesPojo cities() {
        // TODO: 16.01.2020 CREATE IMPL
        return new CitiesPojo(new ArrayList<>() {{ add(new CityPojo("cId1", "City1")); }});
//        try {
//            DepartmentsCreator departmentsCreator = departmentsService.departmentsCreatorProducer()
//                    .getDepartmentsCreatorDefaultInstance(
//                            departmentsService.departmentsRepositoryProducer()
//                                    .getDepartmentsRepositoryDefaultInstance(
//                                            departmentsService.departmentProducer(),
//                                            departmentsService.departmentsProducer(),
//                                            sqlQueryCreatorService.sqlQueryCreatorFromFileString()));
//            DepartmentsPojo departmentsPojo = (DepartmentsPojo) departmentsCreator.create().formForSend();
//            logger.info(CitiesController.class, "Site request departments");
//            return departmentsPojo;
//        } catch (CreatorException ex) {
//            logger.error(CitiesController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
//            return new DepartmentsPojo(new ArrayList<>());
//        }
    }
}
