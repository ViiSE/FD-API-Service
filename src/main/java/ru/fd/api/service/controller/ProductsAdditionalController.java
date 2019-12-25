/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fd.api.service.AttributeGroupsService;
import ru.fd.api.service.CategoriesService;
import ru.fd.api.service.StatusesService;
import ru.fd.api.service.UnitsService;
import ru.fd.api.service.creator.*;
import ru.fd.api.service.data.*;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;
import ru.fd.api.service.producer.creator.AttributesCreatorProducer;
import ru.fd.api.service.producer.repository.AttributesRepositoryProducer;

import java.util.ArrayList;

@Api(tags="Products Controller (Additional)", description = "Контроллер точек для работы с зависимостями товаров")
@Controller
public class ProductsAdditionalController {

    private final StatusesService statusesService;

    private final AttributesCreatorProducer attrCrProducer;
    private final AttributesRepositoryProducer attrRepoProducer;

    private final AttributeGroupsService attrGrsService;
    private final UnitsService unitsService;
    private final CategoriesService categoriesService;

    private final SQLQueryCreator<String, String> sqlQueryCreator;

    private final LoggerService logger;

    public ProductsAdditionalController(
            StatusesService statusesService,
            AttributesCreatorProducer attrCrProducer,
            AttributesRepositoryProducer attrRepoProducer,
            AttributeGroupsService attrGrsService,
            UnitsService unitsService,
            CategoriesService categoriesService,
            SQLQueryCreator<String, String> sqlQueryCreator,
            LoggerService logger) {
        this.statusesService = statusesService;
        this.attrCrProducer = attrCrProducer;
        this.attrRepoProducer = attrRepoProducer;
        this.attrGrsService = attrGrsService;
        this.unitsService = unitsService;
        this.categoriesService = categoriesService;
        this.sqlQueryCreator = sqlQueryCreator;
        this.logger = logger;
    }

    @ApiOperation(value = "Выгружает все возможные статусы товаров")
    @GetMapping("/products/statuses")
    @ResponseBody
    public StatusesPojo statuses() {
        try {
            StatusesCreator statusesCreator = statusesService.statusesCreatorProducer()
                    .getStatusesCreatorDefaultInstance(
                            statusesService.statusesRepositoryProducer().getStatusesRepositoryDefaultInstance(
                                    statusesService.statusProducer(),
                                    statusesService.statusesProducer(),
                                    sqlQueryCreator));
            StatusesPojo statusesPojo = (StatusesPojo) statusesCreator.create().formForSend();
            logger.info(ProductsAdditionalController.class, "Site request statuses");
            return statusesPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsAdditionalController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new StatusesPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "Выгружает все возможные атрибуты товаров")
    @GetMapping("/products/attributes")
    @ResponseBody
    public AttributesPojo attributes() {
        try {
            AttributesCreator attributesCreator = attrCrProducer.getAttributesCreatorDefaultInstance(
                    attrRepoProducer.getAttributesRepositoryDefaultInstance());
            AttributesPojo attributesPojo = (AttributesPojo) attributesCreator.create().formForSend();
            logger.info(ProductsAdditionalController.class, "Site request attributes");
            return attributesPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsAdditionalController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new AttributesPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "Выгружает все возможные группы атрибутов товаров")
    @GetMapping("/products/attribute-groups")
    @ResponseBody
    public AttributeGroupsPojo attributesGroups() {
        try {
            AttributeGroupsCreator attributesGroupsCreator = attrGrsService.attributeGroupsCreatorProducer()
                    .getAttributeGroupsCreatorDefaultInstance(
                            attrGrsService.attributeGroupsRepositoryProducer().getAttributeGroupsRepositoryDefaultInstance(
                                    attrGrsService.attributeGroupProducer(),
                                    attrGrsService.attributeGroupsProducer(),
                                    sqlQueryCreator));
            AttributeGroupsPojo attributesGroupsPojo = (AttributeGroupsPojo) attributesGroupsCreator.create().formForSend();
            logger.info(ProductsAdditionalController.class, "Site request attribute groups");
            return attributesGroupsPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsAdditionalController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new AttributeGroupsPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "Выгружает все возможные единицы измерения товаров")
    @GetMapping("/products/units")
    @ResponseBody
    public UnitsPojo units() {
        try {
            UnitsCreator unitsCreator = unitsService.unitsCreatorProducer().getUnitsCreatorDefaultInstance(
                    unitsService.unitsRepositoryProducer().getUnitsRepositoryDefaultInstance(
                            unitsService.unitProducer(),
                            unitsService.unitsProducer(),
                            sqlQueryCreator));
            UnitsPojo unitsPojo = (UnitsPojo) unitsCreator.create().formForSend();
            logger.info(ProductsAdditionalController.class, "Site request units");
            return unitsPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsAdditionalController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new UnitsPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "Выгружает все возможные категории товаров")
    @GetMapping("/products/categories")
    @ResponseBody
    public CategoriesPojo categories() {
        try {
            CategoriesCreator categoriesCreator = categoriesService.categoriesCreatorProducer()
                    .getCategoriesCreatorDefaultInstance(
                            categoriesService.categoriesRepositoryProducer()
                                    .getCategoriesRepositoryDefaultInstance(
                                            categoriesService.categoryProducer(),
                                            categoriesService.categoriesProducer(),
                                            sqlQueryCreator));
            CategoriesPojo categoriesPojo = (CategoriesPojo) categoriesCreator.create().formForSend();
            logger.info(ProductsAdditionalController.class, "Site request categories");
            return categoriesPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsAdditionalController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new CategoriesPojo(new ArrayList<>());
        }
    }
}
