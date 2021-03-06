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

import org.springframework.stereotype.Component;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.CreateOrderInternalServerErrorException;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.repository.OrderRepository;

@Component("psCreateOrder")
public class PsCreateOrderImpl implements Process<OrderResponse, Order> {

    private final OrderRepository<OrderResponse, Order> oRepo;

    public PsCreateOrderImpl(OrderRepository<OrderResponse, Order> oRepo) {
        this.oRepo = oRepo;
    }

    @Override
    public OrderResponse answer(Order order) throws ProcessException {
        try {
            return oRepo.insert(order);
        } catch (RepositoryException ex) {
            throw new ProcessException(ex.getMessage(), new CreateOrderInternalServerErrorException(ex.getMessage()));
        }
    }
}
