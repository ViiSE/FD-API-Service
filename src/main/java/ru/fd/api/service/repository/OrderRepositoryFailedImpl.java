/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.ExceptionWithSendMessage;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.OrderResponseProducer;

import java.util.ArrayList;
import java.util.List;

@Repository("orderRepositoryFailed")
@Scope("prototype")
public class OrderRepositoryFailedImpl implements OrderRepository<Void, OrderResponse> {

    private final OrderResponseProducer orderResponseProducer;
    private final ExceptionWithSendMessage exception;

    public OrderRepositoryFailedImpl(OrderResponseProducer orderResponseProducer, ExceptionWithSendMessage exception) {
        this.orderResponseProducer = orderResponseProducer;
        this.exception = exception;
    }

    @Override
    public Void insert() throws RepositoryException {
        throw new RepositoryException("Cannot insert in OrderRepositoryFailed instance");
    }

    @Override
    public OrderResponse read(long id) {
        return orderResponseProducer.getOrderResponseWithExceptionMessageInstance(
                orderResponseProducer.getOrderResponseWithMessageInstance(
                        orderResponseProducer.getOrderResponseSimpleInstance(id, (short) 500),
                        exception.getMessageForSend()),
                exception.getMessage());
    }

    @Override
    public List<OrderResponse> readAll() throws RepositoryException {
        throw new RepositoryException("Cannot readAll in OrderRepositoryFailed instance");
    }

    @Override
    public List<OrderResponse> readFirst(int sliceSize) throws RepositoryException {
        throw new RepositoryException("Cannot readFirst in OrderRepositoryFailed instance");
    }
}
