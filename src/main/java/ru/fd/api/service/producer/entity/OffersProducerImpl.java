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
import ru.fd.api.service.entity.Offer;
import ru.fd.api.service.entity.Offers;

import java.util.List;

@Service("offersProducer")
public class OffersProducerImpl implements OffersProducer {

    private final ApplicationContext ctx;

    public OffersProducerImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Offers getOffersInstance(List<Offer> offers) {
        return (Offers) ctx.getBean("offers", offers);
    }
}
