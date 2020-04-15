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
import ru.fd.api.service.data.OrderPojo;

import java.util.ArrayList;
import java.util.List;

@Component("recipientOrder")
@Scope("prototype")
public class RecipientOrderImpl implements Recipient {

    private final long siteId;
    private final Order order;

    public RecipientOrderImpl(Order order, long siteId) {
        this.order = order;
        this.siteId = siteId;
    }

    @Override
    public List<String> addresses() {
        List<String> addresses = new ArrayList<>();

        OrderPojo orderPojo = (OrderPojo) order.formForSend();
        String email = orderPojo.getCustomer().getEmail();
        String phoneNumber = orderPojo.getCustomer().getPhoneNumber();

        if(!email.isEmpty())
            addresses.add(email);

        if(!phoneNumber.isEmpty())
            addresses.add(phoneNumber);

        return addresses;
    }


}
