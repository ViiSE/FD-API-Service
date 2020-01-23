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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "OrderResponse", description = "Ответ API при оформлении заказа", reference = "[POST] /orders")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderResponsePojo {

    @ApiModelProperty(notes = "Статус заказа. Возможные значения:" +
            "\n<b>0</b> - не обработан," +
            "\n<b>1</b> - готов к сборке," +
            "\n<b>2</b> - нехватка остатков," +
            "\n<b>3</b> - собран," +
            "\n<b>4</b> - отменен," +
            "\n<b>5</b> - выполнен", position = 1)
    private short status;
    @ApiModelProperty(notes = "ID заказа", position = 2)
    private long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "Сообщение от API", position = 3)
    private String message;
//    @JsonProperty("products")
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    @ApiModelProperty(notes = "Товары, остатков которых не хватило для оформления заказа", position = 4)
//    private ProductsOrderPojo productsOrderPojo;
    @JsonIgnore
    private String exMessage;

    public void setStatus(short status) {
        this.status = status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    @JsonProperty("products")
//    public void setProductsOrderPojo(ProductsOrderPojo productsOrderPojo) {
//        this.productsOrderPojo = productsOrderPojo;
//    }

    public void setExMessage(String exMessage) {
        this.exMessage = exMessage;
    }

    public short getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

//    @JsonProperty("products")
//    public List<ProductOrderPojo> getProductsOrder() {
//        return productsOrderPojo != null ? productsOrderPojo.getProducts() : null;
//    }

    @JsonIgnore
    public String getExMessage() {
        return exMessage;
    }
}
