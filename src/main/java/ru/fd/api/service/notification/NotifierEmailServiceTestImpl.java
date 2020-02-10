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
import ru.fd.api.service.data.CustomerPojo;
import ru.fd.api.service.entity.Customer;

@Component("notifierEmailServiceTest")
public class NotifierEmailServiceTestImpl implements NotifierEmailService<Customer> {

    @Override
    public void notify(Customer consumer) {
        CustomerPojo customerPojo = (CustomerPojo) consumer.formForSend();
        System.out.println("Send to email " + customerPojo.getEmail());
    }
}
