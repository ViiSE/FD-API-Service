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

package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.DateOfferPojo;

import java.time.LocalDateTime;

@Component("dateOfferFinishesAt")
@Scope("prototype")
public class DateOfferFinishesAtImpl implements DateOffer {

    private final DateOffer dateOffer;
    private final LocalDateTime finishesAt;

    public DateOfferFinishesAtImpl(DateOffer dateOffer, LocalDateTime finishesAt) {
        this.dateOffer = dateOffer;
        this.finishesAt = finishesAt;
    }

    @Override
    public Object formForSend() {
        DateOfferPojo dateOfferPojo = (DateOfferPojo) dateOffer.formForSend();
        dateOfferPojo.setFinishesAt(finishesAt);

        return dateOfferPojo;
    }
}
