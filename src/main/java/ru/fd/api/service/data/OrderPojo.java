/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel("Order")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderPojo {

    private final long id;
    private final String cityId;
    private final CustomerPojo customer;
    private final DeliveryPojo delivery;
    private final short payTypeId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD HH:mm:ss")
    private final LocalDateTime dateTime;

    private ProductsOrderPojo products;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String comment;

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
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public String getCityId() {
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
        return products.getProducts();
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setProducts(ProductsOrderPojo products) {
        this.products = products;
    }
}
