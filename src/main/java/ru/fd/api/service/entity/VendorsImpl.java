/*
 * Copyright 2019 ViiSE
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

package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.VendorPojo;
import ru.fd.api.service.data.VendorsPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("vendors")
@Scope("prototype")
public class VendorsImpl implements Vendors {

    private final List<Vendor> vendors;

    public VendorsImpl(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    @Override
    public Object formForSend() {
        List<VendorPojo> vendorPojo = vendors.stream()
                .map(vendor -> (VendorPojo) vendor.formForSend())
                .collect(Collectors.toList());
        return new VendorsPojo(vendorPojo);
    }
}
