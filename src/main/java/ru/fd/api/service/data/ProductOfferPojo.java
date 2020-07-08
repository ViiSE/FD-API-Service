/*
 * Copyright 2020 ViiSE
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ProductOffer", description = "Товар, участвующий в акции")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOfferPojo {

    @ApiModelProperty(notes = "GID товара", position = 1)
    private String id;
    @ApiModelProperty(notes = "Цены", position = 2)
    private PricesOfferPojo prices;
    @ApiModelProperty(notes = "ID акции", position = 3)
    private long offerId;

    public void setId(String id) {
        this.id = id;
    }

    public void setPrices(PricesOfferPojo prices) {
        this.prices = prices;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public String getId() {
        return id;
    }

    public PricesOfferPojo getPrices() {
        return prices;
    }

    public long getOfferId() {
        return offerId;
    }
}
