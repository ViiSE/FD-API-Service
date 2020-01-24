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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fd.api.service.creator.*;
import ru.fd.api.service.data.*;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;

import java.util.ArrayList;

@Api(tags="Products Controller (Additional)", description = "Контроллер точек для работы с зависимостями товаров")
@RestController
public class ProductsAdditionalController {

    private final StatusesCreator statusesCreator;
    private final AttributesCreator attributesCreator;
    private final AttributeGroupsCreator attributeGroupsCreator;
    private final UnitsCreator unitsCreator;
    private final CategoriesCreator categoriesCreator;
    private final LoggerService logger;

    public ProductsAdditionalController(
            StatusesCreator statusesCreator,
            AttributesCreator attributesCreator,
            AttributeGroupsCreator attributeGroupsCreator,
            UnitsCreator unitsCreator,
            CategoriesCreator categoriesCreator,
            LoggerService logger) {
        this.statusesCreator = statusesCreator;
        this.attributesCreator = attributesCreator;
        this.attributeGroupsCreator = attributeGroupsCreator;
        this.unitsCreator = unitsCreator;
        this.categoriesCreator = categoriesCreator;
        this.logger = logger;
    }

    @ApiOperation(value = "[!!!НЕДОСТУПНО!!!] Выгружает все возможные статусы товаров")
    @GetMapping("/products/statuses")
    public StatusesPojo statuses() {
        try {
            StatusesPojo statusesPojo = (StatusesPojo) statusesCreator.create().formForSend();
            logger.info(ProductsAdditionalController.class, "Site request statuses");
            return statusesPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsAdditionalController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new StatusesPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "[!!!НЕДОСТУПНО!!!] Выгружает все возможные атрибуты товаров")
    @GetMapping("/products/attributes")
    public AttributesPojo attributes() {
        try {
            AttributesPojo attributesPojo = (AttributesPojo) attributesCreator.create().formForSend();
            logger.info(ProductsAdditionalController.class, "Site request attributes");
            return attributesPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsAdditionalController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new AttributesPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "[!!!НЕДОСТУПНО!!!] Выгружает все возможные группы атрибутов товаров")
    @GetMapping("/products/attribute-groups")
    public AttributeGroupsPojo attributesGroups() {
        try {
            AttributeGroupsPojo attributesGroupsPojo = (AttributeGroupsPojo) attributeGroupsCreator.create().formForSend();
            logger.info(ProductsAdditionalController.class, "Site request attribute groups");
            return attributesGroupsPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsAdditionalController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new AttributeGroupsPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "Выгружает все возможные единицы измерения товаров")
    @GetMapping("/products/units")
    public UnitsPojo units() {
        try {
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
    public CategoriesPojo categories() {
        try {
            CategoriesPojo categoriesPojo = (CategoriesPojo) categoriesCreator.create().formForSend();
            logger.info(ProductsAdditionalController.class, "Site request categories");
            return categoriesPojo;
        } catch (CreatorException ex) {
            logger.error(ProductsAdditionalController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            return new CategoriesPojo(new ArrayList<>());
        }
    }
}
