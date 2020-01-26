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
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.log.LoggerService;
import ru.fd.api.service.repository.CustomerRepository;

import java.util.List;

@Component("notifierDatabaseServiceDefault")
public class NotifierDatabaseServiceDefaultImpl implements NotifierDatabaseService<List<Customer>> {

    private final LoggerService logger;
    private final CustomerRepository customerRepository;

    public NotifierDatabaseServiceDefaultImpl(LoggerService logger, CustomerRepository customerRepository) {
        this.logger = logger;
        this.customerRepository = customerRepository;
    }

    @Override
    public void notify(List<Customer> customers) {
        try {
            customerRepository.update(customers);
            System.out.println("Database updated");
        } catch (RepositoryException ex) {
            logger.error(NotifierDatabaseService.class, ex.getMessage());
        }
    }
}
