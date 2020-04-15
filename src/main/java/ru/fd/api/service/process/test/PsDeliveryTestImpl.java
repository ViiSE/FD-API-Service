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

package ru.fd.api.service.process.test;

import ru.fd.api.service.entity.*;
import ru.fd.api.service.process.Process;

import java.time.LocalDate;

public class PsDeliveryTestImpl implements Process<Delivery, Void> {

    @Override
    public Delivery answer(Void v) {
        return new DeliveryWithDateImpl(
                new DeliveryWithTimeIdImpl(
                        new DeliveryWithDepartmentIdImpl(
                                new DeliverySimpleImpl(
                                        (short) 0,
                                        1,
                                        "st. Example, 404"),
                                "dep_1"),
                        (short) 0),
                LocalDate.now());
    }
}
