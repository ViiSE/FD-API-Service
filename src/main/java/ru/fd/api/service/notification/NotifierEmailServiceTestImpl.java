/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.notification;

import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.entity.CustomerWithEmailImpl;

@Component("notifierEmailServiceTest")
public class NotifierEmailServiceTestImpl implements NotifierEmailService<Customer> {

    @Override
    public void notify(Customer consumer) {
        CustomerWithEmailImpl customer = (CustomerWithEmailImpl) consumer;
        System.out.println("Send to email " + customer.email());
    }
}
