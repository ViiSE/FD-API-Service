/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service;

import org.springframework.stereotype.Service;
import ru.fd.api.service.producer.creator.CustomerCreatorProducer;
import ru.fd.api.service.producer.entity.CustomerProducer;

@Service("customerServiceDefault")
public class CustomerServiceDefaultImpl implements CustomerService {

    private final CustomerCreatorProducer customerCreatorProducer;
    private final CustomerProducer customerProducer;

    public CustomerServiceDefaultImpl(CustomerCreatorProducer customerCreatorProducer, CustomerProducer customerProducer) {
        this.customerCreatorProducer = customerCreatorProducer;
        this.customerProducer = customerProducer;
    }

    @Override
    public CustomerCreatorProducer customerCreatorProducer() {
        return customerCreatorProducer;
    }

    @Override
    public CustomerProducer customerProducer() {
        return customerProducer;
    }
}
