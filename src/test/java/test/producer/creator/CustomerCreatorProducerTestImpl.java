/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.producer.creator;

import ru.fd.api.service.creator.CustomerCreator;
import ru.fd.api.service.creator.CustomerCreatorEmailOrPhoneRequiredImpl;
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.producer.creator.CustomerCreatorProducer;
import ru.fd.api.service.producer.entity.CustomerProducer;

public class CustomerCreatorProducerTestImpl implements CustomerCreatorProducer {

    @Override
    public CustomerCreator getCustomerCreatorEmailOrPhoneRequiredInstance(
            CustomerPojo customerPojo,
            CustomerProducer customerProducer) {
        return new CustomerCreatorEmailOrPhoneRequiredImpl(customerPojo, customerProducer);
    }
}
