/*
 * Copyright 2019 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.fd.api.service.process;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.fd.api.service.constant.OrderStatuses;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.OrderRepository;
import ru.fd.api.service.repository.ProductsRepository;

import java.util.List;

@Component("psChangedBalances")
public class PsChangedBalancesImpl implements Process<Products, Void> {

    private final OrderRepository<Void, Order> oRepo;
    private final ProductsRepository pRepo;

    public PsChangedBalancesImpl(
            OrderRepository<Void, Order> oRepo,
            @Qualifier("productsRepositoryWithChangedBalances") ProductsRepository pRepo) {
        this.oRepo = oRepo;
        this.pRepo = pRepo;
    }

    @Override
    public Products answer(Void nothing) throws ProcessException {
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
            throw new ProcessException(ex.getMessage(), ex);
        }
    }

    private void sleep() {
        try { Thread.sleep(500); } catch (InterruptedException ignore) {}
    }
}
