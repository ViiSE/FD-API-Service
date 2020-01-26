/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository;

import ru.fd.api.service.entity.Customer;
import ru.fd.api.service.exception.RepositoryException;

import java.util.List;

public interface CustomerRepository {
    List<Customer> readAll() throws RepositoryException;
    void update(List<Customer> customers) throws RepositoryException;
}
