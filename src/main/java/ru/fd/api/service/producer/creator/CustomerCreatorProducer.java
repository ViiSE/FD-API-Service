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

import ru.fd.api.service.creator.CustomerCreator;
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.producer.entity.CustomerProducer;

public interface CustomerCreatorProducer {
    CustomerCreator getCustomerCreatorEmailOrPhoneRequiredInstance(CustomerPojo customerPojo, CustomerProducer customerProducer);
}
