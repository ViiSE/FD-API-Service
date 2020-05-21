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
import ru.fd.api.service.constant.AdditionalProducts;
import ru.fd.api.service.data.*;
import ru.fd.api.service.entity.Sendable;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.process.Processes;

import java.util.ArrayList;

@Api(tags="Products Controller (Additional)", description = "Контроллер точек для работы с зависимостями товаров")
@RestController
public class ProductsAdditionalController {

    private final Processes<Sendable, Void> processes;

    public ProductsAdditionalController(Processes<Sendable, Void> processes) {
        this.processes = processes;
    }

    @ApiOperation(value = "[!!!НЕДОСТУПНО!!!] Выгружает все возможные статусы товаров")
    @GetMapping("/products/statuses")
    public StatusesPojo statuses() {
        try {
            return (StatusesPojo) processes.process(AdditionalProducts.STATUSES).answer(null).formForSend();
        } catch (ProcessException ex) {
            return new StatusesPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "Выгружает все возможные атрибуты товаров")
    @GetMapping("/products/attributes")
    public AttributesPojo attributes() {
        try {
            return (AttributesPojo) processes.process(AdditionalProducts.ATTRIBUTES).answer(null).formForSend();
        } catch (ProcessException ex) {
            return new AttributesPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "Выгружает все возможные группы атрибутов товаров")
    @GetMapping("/products/attribute-groups")
    public AttributeGroupsPojo attributesGroups() {
        try {
            return (AttributeGroupsPojo) processes.process(AdditionalProducts.ATTRIBUTE_GROUPS).answer(null).formForSend();
        } catch (ProcessException ex) {
            return new AttributeGroupsPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "Выгружает все возможные единицы измерения товаров")
    @GetMapping("/products/units")
    public UnitsPojo units() {
        try {
            return (UnitsPojo) processes.process(AdditionalProducts.UNITS).answer(null).formForSend();
        } catch (ProcessException ex) {
            return new UnitsPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "Выгружает все возможные категории товаров")
    @GetMapping("/products/categories")
    public CategoriesPojo categories() {
        try {
            return (CategoriesPojo) processes.process(AdditionalProducts.CATEGORIES).answer(null).formForSend();
        } catch (ProcessException ex) {
            return new CategoriesPojo(new ArrayList<>());
        }
    }
}
