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

import java.util.List;

@Component("notifierDefault")
public class NotifierDefaultImpl implements Notifier<Customer> {

    private final NotifierDatabaseService<List<Customer>> databaseService;
    private final NotifierEmailService<Customer> emailService;
    private final NotifierSMSService<Customer> smsService;

    public NotifierDefaultImpl(
            NotifierDatabaseService<List<Customer>> databaseService,
            NotifierEmailService<Customer> emailService,
            NotifierSMSService<Customer> smsService) {
        this.databaseService = databaseService;
        this.emailService = emailService;
        this.smsService = smsService;
    }

    @Override
    public void notify(List<Customer> consumers) {
        for(Customer consumer: consumers) {
            CustomerPojo customerPojo = (CustomerPojo) consumer.formForSend();
            if(!customerPojo.getEmail().isEmpty())
                emailService.notify(consumer);

            if(!customerPojo.getPhoneNumber().isEmpty())
                smsService.notify(consumer);
        }

        databaseService.notify(consumers);
    }
}
