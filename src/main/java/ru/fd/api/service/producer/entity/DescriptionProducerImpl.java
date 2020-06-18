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

package ru.fd.api.service.producer.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.entity.Description;

@Service("descriptionProducer")
public class DescriptionProducerImpl implements DescriptionProducer {

    private final ApplicationContext ctx;

    public DescriptionProducerImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Description getDescriptionShortInstance(String description) {
        return (Description) ctx.getBean("descriptionShort", description);
    }

    @Override
    public Description getDescriptionFullInstance(Description description, String fullDescription) {
        return (Description) ctx.getBean("descriptionFull", description, fullDescription);
    }

    @Override
    public Description getDescriptionWithProductIdInstance(Description description, String productId) {
        return (Description) ctx.getBean("descriptionWithProductId", description, productId);
    }
}
