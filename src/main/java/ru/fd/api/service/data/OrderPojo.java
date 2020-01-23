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
    private final Long id;
    @ApiModelProperty(notes = "ID города заказа", position = 2, required = true)
    private Integer cityId = -1;
    @ApiModelProperty(notes = "Покупатель, оформивший заказ", position = 3, required = true)
    private CustomerPojo customer;
    @ApiModelProperty(position = 4, required = true)
    private DeliveryPojo delivery;
    @ApiModelProperty(notes = "ID типа оплаты заказа. Возможные значения:\n" +
            "<b>0</b> - оплата на месте,\n" +
            "<b>1</b> - оплата на сайте по безналичному расчету", position = 5, required = true)
    private Short payTypeId = -1;

    @ApiModelProperty(example = "2020-01-13 00:45:36",
            notes = "Дата и время оформления доставки (yyyy-MM-dd HH:mm:ss). Значение по умолчанию - <i>текущие дата и время</i>",
            position = 6)
    private LocalDateTime dateTime = LocalDateTime.now();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "Коментарий покупателя к заказу", position = 7)
    private String comment;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "Список товаров заказа", position = 8, required = true)
    private List<ProductOrderPojo> products;

    // TODO: 20.01.2020 FUTURE
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    @ApiModelProperty(notes = "Список действительных остатков товара (указывается при статусе заказа <b>2</b>)", position = 9)
//    private List<ProductOrderPojo> productsActual;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "Статус заказа. Возможные значения:" +
            "\n<b>0</b> - не обработан," +
            "\n<b>1</b> - готов к сборке," +
            "\n<b>2</b> - нехватка остатков," +
            "\n<b>3</b> - собран," +
            "\n<b>4</b> - отменен," +
            "\n<b>5</b> - выполнен", position = 10)
    private Short status = 0;

    @JsonCreator
    public OrderPojo(@JsonProperty("id") Long id) {
        this.id = Objects.requireNonNullElse(id, -1L);
    }

    public long getId() {
        return id;
    }

    public int getCityId() {
        return cityId;
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

    // TODO: 20.01.2020 FUTURE
//    public List<ProductOrderPojo> getProductsActual() {
//        return productsActual;
//    }

    public short getStatus() {
        return status;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void setCustomer(CustomerPojo customer) {
        this.customer = customer;
    }

    public void setDelivery(DeliveryPojo delivery) {
        this.delivery = delivery;
    }

    public void setPayTypeId(Short payTypeId) {
        this.payTypeId = payTypeId;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setProducts(List<ProductOrderPojo> products) {
        this.products = products;
    }

    // TODO: 20.01.2020 FUTURE
//    public void setProductsActual(List<ProductOrderPojo> productsActual) {
//        this.productsActual = productsActual;
//    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
