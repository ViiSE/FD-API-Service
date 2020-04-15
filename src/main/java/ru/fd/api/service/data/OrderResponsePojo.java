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

package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
