/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

// TODO: 23.01.2020 FUTURE
//@ApiModel(value = "Orders", description = "Заказы")
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrdersPojo {

//    @ApiModelProperty(notes = "Список заказов", position = 1)
    private final List<OrderPojo> orders;

    @JsonCreator
    public OrdersPojo(
//            @JsonProperty("orders")
            List<OrderPojo> orders) {
        this.orders = orders;
    }

    public List<OrderPojo> getOrders() {
        return orders;
    }
}
