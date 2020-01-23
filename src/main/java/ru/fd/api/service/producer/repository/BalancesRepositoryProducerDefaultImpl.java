/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.producer.repository;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.repository.BalancesRepository;

@Service("balancesRepositoryProducerDefault")
public class BalancesRepositoryProducerDefaultImpl implements BalancesRepositoryProducer {

    private final ApplicationContext ctx;

    public BalancesRepositoryProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public BalancesRepository getBalancesRepositoryDefaultInstance() {
        return ctx.getBean("balancesRepositoryDefault", BalancesRepository.class);
    }
}
