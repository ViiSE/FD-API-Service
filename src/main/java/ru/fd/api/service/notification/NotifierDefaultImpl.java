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
import ru.fd.api.service.entity.CustomerWithPhoneNumberImpl;

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
            if(consumer instanceof CustomerWithEmailImpl)
                emailService.notify(consumer);
            else if(consumer instanceof CustomerWithPhoneNumberImpl)
                smsService.notify(consumer);
        }

        databaseService.notify(consumers);
    }
}
