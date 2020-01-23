/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.entity;

import org.springframework.stereotype.Component;

@Component("orderEmpty")
public class OrderEmptyImpl implements Order {

    @Override
    public Object formForSend() {
        return "{}";
    }

    @Override
    public long id() {
        return -1L;
    }

    @Override
    public short status() {
        return -1;
    }
}
