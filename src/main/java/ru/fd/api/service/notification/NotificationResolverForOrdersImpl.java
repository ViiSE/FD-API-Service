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
import ru.fd.api.service.entity.CustomerWithNotified;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.log.LoggerService;
import ru.fd.api.service.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Component("notificationResolverForOrders")
public class NotificationResolverForOrdersImpl implements NotificationResolver<Customer> {

    private final LoggerService logger;
    private final CustomerRepository customerRepository;

    public NotificationResolverForOrdersImpl(
            LoggerService logger,
            CustomerRepository customerRepository) {
        this.logger = logger;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> consumers() {
        List<Customer> notNotifiedCustomers = new ArrayList<>();
        try {
            List<Customer> customers = customerRepository.readAll();
            for(Customer customer: customers) {
                CustomerWithNotified customerWN = (CustomerWithNotified) customer;
                if(!customerWN.isNotified())
                    notNotifiedCustomers.add(customer);
            }
        } catch (RepositoryException ex) {
            logger.error(NotificationResolver.class, ex.getMessage());
        }

        return notNotifiedCustomers;
    }
}
