/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApiModel(value = "Order", description = "Заказ")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderPojo {

    @ApiModelProperty(notes = "ID заказа", position = 1, required = true)
    private final long id;
    @ApiModelProperty(notes = "GID города заказа", position = 2, required = true)
    private final String cityId;
    @ApiModelProperty(notes = "Покупатель, оформивший заказ", position = 3, required = true)
    private final CustomerPojo customer;
    @ApiModelProperty(position = 4, required = true)
    private final DeliveryPojo delivery;
    @ApiModelProperty(notes = "ID типа оплаты заказа. Возможные значения:\n" +
            "<b>0</b> - оплата на месте,\n" +
            "<b>1</b> - оплата на сайте по безналичному расчету", position = 5, required = true)
    private final short payTypeId;

//    @JsonSerialize(using = LocalDateTimeDefaultSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDefaultDeserializer.class)
    @ApiModelProperty(example = "2020-01-13 00:45:36",
            notes = "Дата и время оформления доставки (yyyy-MM-dd HH:mm:ss). Значение по умолчанию - <i>текущие дата и время</i>",
            position = 6)
    private final LocalDateTime dateTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "Коментарий покупателя к заказу", position = 7)
    private String comment;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "Список товаров заказа", position = 8, required = true)
    private List<ProductOrderPojo> products;

    @JsonCreator
    public OrderPojo(
            @JsonProperty("id") long id,
            @JsonProperty("city_id") String cityId,
            @JsonProperty("customer") CustomerPojo customer,
            @JsonProperty("delivery") DeliveryPojo delivery,
            @JsonProperty("pay_type_id") short payTypeId,
            @JsonProperty("date_time") LocalDateTime dateTime) {
        this.id = id;
        this.cityId = cityId;
        this.customer = customer;
        this.payTypeId = payTypeId;
        this.delivery = delivery;
        this.dateTime = Objects.requireNonNullElseGet(dateTime, LocalDateTime::now);
    }

    public long getId() {
        return id;
    }

    public String getCityId() {
        return cityId == null ? "": cityId;
    }

    public CustomerPojo getCustomer() {
        return customer;
    }

    public DeliveryPojo getDelivery() {
        return delivery;
    }

    public short getPayTypeId() {
        return payTypeId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getComment() {
        return comment == null ? "" : comment;
    }

    public List<ProductOrderPojo> getProducts() {
        return products != null ? products : new ArrayList<>();
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setProducts(List<ProductOrderPojo> products) {
        this.products = products;
    }
}
