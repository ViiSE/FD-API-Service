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

import java.util.ArrayList;

public class PsVendorsTestImpl implements Process<Sendable, Void> {

    @Override
    public Sendable answer(Void v) {
        Vendor vendor1 = new VendorWithCodeAvardaImpl(
                new VendorImpl("ven1", "Vendor 1"),
                "ca1");
        Vendor vendor2 = new VendorWithCodeAvardaImpl(
                new VendorImpl("ven2", "Vendor 2"),
                "");
        Vendor vendor3 = new VendorImpl("ven1", "Vendor 3");
        return new VendorsImpl(new ArrayList<>() {{ add(vendor1); add(vendor2); add(vendor3); }});

    }
}
