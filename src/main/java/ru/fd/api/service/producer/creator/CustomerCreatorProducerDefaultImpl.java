/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.producer.creator;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.creator.CustomerCreator;
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.producer.entity.CustomerProducer;

@Service("customerCreatorProducerDefault")
public class CustomerCreatorProducerDefaultImpl implements CustomerCreatorProducer {

    private final ApplicationContext ctx;

    public CustomerCreatorProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public CustomerCreator getCustomerCreatorEmailOrPhoneRequiredInstance(
            CustomerPojo customerPojo,
            CustomerProducer customerProducer) {
        return (CustomerCreator) ctx.getBean("customerCreatorEmailOrPhoneRequired", customerPojo, customerProducer);
    }
}
