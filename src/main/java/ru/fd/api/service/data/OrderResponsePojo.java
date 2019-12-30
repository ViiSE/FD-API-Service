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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderResponsePojo {

    private short status;
    private String message;
    @JsonProperty("products_order")
    private ProductsOrderPojo productsOrderPojo;

    public void setStatus(short status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("products_order")
    public void setProductsOrderPojo(ProductsOrderPojo productsOrderPojo) {
        this.productsOrderPojo = productsOrderPojo;
    }

    public short getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @JsonProperty("products_order")
    public List<ProductOrderPojo> getProductsOrder() {
        return productsOrderPojo.getProducts();
    }
}
