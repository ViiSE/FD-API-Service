/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.constant.OrderStatuses;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.BalanceProducer;
import ru.fd.api.service.producer.entity.BalancesProducer;
import ru.fd.api.service.producer.entity.ProductProducer;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.producer.repository.ProductsRepositoryProducer;
import ru.fd.api.service.repository.OrderRepository;

@Component("changedBalancesRepositoryWithOrderIdProcessor")
@Scope("prototype")
public class ChangedBalancesRepositoryWithOrderIdProcessorImpl implements ProductsRepositoryProcessor {

    private final OrderRepository<Void, Order> oRepo;
    private final ProductsRepositoryProducer pRepoProducer;
    private final ProductProducer pProducer;
    private final ProductsProducer psProducer;
    private final BalanceProducer bProducer;
    private final BalancesProducer bsProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public ChangedBalancesRepositoryWithOrderIdProcessorImpl(
            OrderRepository<Void, Order> oRepo,
            ProductsRepositoryProducer pRepoProducer,
            ProductProducer pProducer,
            ProductsProducer psProducer,
            BalanceProducer bProducer,
            BalancesProducer bsProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.oRepo = oRepo;
        this.pRepoProducer = pRepoProducer;
        this.pProducer = pProducer;
        this.psProducer = psProducer;
        this.bProducer = bProducer;
        this.bsProducer = bsProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Products apply(Object orderIdObj) {
        Long orderId = (Long) orderIdObj;
        Order order;
        try {
            boolean isDone = false;
            do {
                order = oRepo.read(orderId);
                if(order.status() != OrderStatuses.NOT_PROCESSED)
                    isDone = true;
                else
                    sleep();
            } while(!isDone);

            return pRepoProducer.getProductsRepositoryWithChangedBalancesAndOrderInstance(
                    order,
                    pProducer,
                    psProducer,
                    bProducer,
                    bsProducer,
                    sqlQueryCreator).readProducts();
        } catch (RepositoryException ex) {
            return psProducer.getProductsFailedInstance(ex.getMessage());
        }
    }

    private void sleep() {
        try { Thread.sleep(500); } catch (InterruptedException ignore) {}
    }
}
