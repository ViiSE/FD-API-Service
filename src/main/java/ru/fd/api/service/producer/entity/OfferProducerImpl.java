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

package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.DateOffer;
import ru.fd.api.service.entity.Department;
import ru.fd.api.service.entity.Offer;

import java.util.List;

@Service("offerProducer")
public class OfferProducerImpl implements OfferProducer {

    private final ApplicationContext ctx;

    public OfferProducerImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Offer getOfferSimpleInstance(long offerId) {
        return (Offer) ctx.getBean("offerSimple", offerId);
    }

    @Override
    public Offer getOfferWithNameInstance(Offer offer, String name) {
        return (Offer) ctx.getBean("offerWithName", offer, name);
    }

    @Override
    public Offer getOfferWithDateOfferInstance(Offer offer, DateOffer dateOffer) {
        return (Offer) ctx.getBean("offerWithDateOffer", offer, dateOffer);
    }

    @Override
    public Offer getOfferWithDepartmentsInstance(Offer offer, List<Department> departments) {
        return (Offer) ctx.getBean("offerWithDepartments", offer, departments);
    }

    @Override
    public Offer getOfferWithRawDepartmentsListIdInstance(Offer offer, String rawDepartmentsListId) {
        return (Offer) ctx.getBean("offerWithRawDepartmentsListId", offer, rawDepartmentsListId);
    }
}
