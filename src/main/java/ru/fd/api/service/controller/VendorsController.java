/*
 * Copyright 2020 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fd.api.service.data.VendorsPojo;
import ru.fd.api.service.entity.Sendable;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.process.Process;

import java.util.ArrayList;

@Api(tags="Vendors Controller", description = "Контроллер точек для работы с производителями", authorizations = {@Authorization(value = "Bearer")})
@RestController
public class VendorsController {

    private final Process<Sendable, Void> process;

    public VendorsController(@Qualifier("psLgVendors") Process<Sendable, Void> process) {
        this.process = process;
    }

    @ApiOperation(value = "Выгружает всех производителей")
    @GetMapping("/vendors")
    public VendorsPojo vendors() {
        try {
            return (VendorsPojo) process.answer(null).formForSend();
        } catch (ProcessException ex) {
            return new VendorsPojo(new ArrayList<>());
        }
    }
}
