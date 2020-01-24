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

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.fd.api.service.constant.OrderStatuses;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.ProductsProducer;
import ru.fd.api.service.repository.OrderRepository;
import ru.fd.api.service.repository.ProductsRepository;

import java.util.List;

@Component("changedBalancesRepositoryProcessor")
public class ChangedBalancesRepositoryProcessorImpl implements ProductsRepositoryProcessor {

    private final OrderRepository<Void, Order> oRepo;
    private final ProductsRepository pRepo;
    private final ProductsProducer pProducer;

    public ChangedBalancesRepositoryProcessorImpl(
            OrderRepository<Void, Order> oRepo,
            @Qualifier("productsRepositoryWithChangedBalances") ProductsRepository pRepo,
            ProductsProducer pProducer) {
        this.oRepo = oRepo;
        this.pRepo = pRepo;
        this.pProducer = pProducer;
    }

    @Override
    public Products apply(Object nothing) {
        try {
            int sliceSize = oRepo.readAll().size();
            boolean isDone = false;
            do {
                List<Order> orders = oRepo.readFirst(sliceSize);
                long checks = 0;

                for(Order order: orders)
                    if (order.status() != OrderStatuses.NOT_PROCESSED)
                        checks++;

                if(checks == sliceSize)
                    isDone = true;
                else
                    sleep();
            } while(!isDone);

            return pRepo.read();
        } catch (RepositoryException ex) {
            return pProducer.getProductsFailedInstance(ex.getMessage());
        }
    }

    private void sleep() {
        try { Thread.sleep(500); } catch (InterruptedException ignore) {}
    }
}
