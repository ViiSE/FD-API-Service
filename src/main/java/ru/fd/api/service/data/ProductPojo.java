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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

@ApiModel(value = "Product", description = "Товар")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductPojo {

    @ApiModelProperty(notes = "GID товара", position = 1)
    private String id;
    @ApiModelProperty(notes = "GID категории", position = 2)
    private String categoryId;
    @ApiModelProperty(notes = "GID производителя", position = 3)
    private String vendorId;
    @ApiModelProperty(notes = "GID страны", position = 4)
    private String countryId;
    @ApiModelProperty(notes = "GID единицы измерения", position = 5)
    private String unitId;
    @ApiModelProperty(notes = "Ставка НДС", position = 6)
    private short tax;
    @ApiModelProperty(notes = "Артикул", position = 7)
    private String articul = "";
    @ApiModelProperty(notes = "Код товара в FD", position = 8)
    private String code;
    @ApiModelProperty(notes = "Наименование", position = 9)
    private String name;
    @ApiModelProperty(notes = "Полное описание товара (по умолчанию пустое значение)", position = 10)
    private String fullDescription = "";
    @ApiModelProperty(notes = "Краткое описание товара (по умолчанию пустое значение)", position = 11)
    private String shortDescription = "";
    @ApiModelProperty(notes = "Код Avarda", position = 12)
    private String codeAvarda;
    @ApiModelProperty(notes = "Цены", position = 13)
    @JsonInclude(JsonInclude.Include.NON_NULL) private PricesPojo prices;
    @ApiModelProperty(notes = "Статусы", position = 14)
    @JsonInclude(JsonInclude.Include.NON_NULL) private ProductStatusesPojo statuses;
    @ApiModelProperty(notes = "Остатки", position = 15)
    @JsonInclude(JsonInclude.Include.NON_NULL) private BalancesPojo balances;
    @ApiModelProperty(notes = "Атрибуты", position = 16)
    @JsonInclude(JsonInclude.Include.NON_NULL) private ProductAttributesPojo attributes;

    public void setId(String id) {
        this.id = id;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public void setTax(short tax) {
        this.tax = tax;
    }

    public void setArticul(String articul) {
        this.articul = Objects.requireNonNullElse(articul, "");
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = Objects.requireNonNullElse(fullDescription, "");
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = Objects.requireNonNullElse(shortDescription, "");
    }

    public void setPrices(PricesPojo prices) {
        this.prices = prices;
    }

    public void setStatuses(ProductStatusesPojo statuses) {
        this.statuses = statuses;
    }

    public void setBalances(BalancesPojo balances) {
        this.balances = balances;
    }

    public void setAttributes(ProductAttributesPojo attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public String getUnitId() {
        return unitId;
    }

    public short getTax() {
        return tax;
    }

    public String getArticul() {
        return articul;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public List<PricePojo> getPrices() {
        return prices != null ? prices.getPrices() : null;
    }

    public List<ProductStatusPojo> getStatuses() {
        return statuses != null ? statuses.getStatuses() : null;
    }

    public List<BalancePojo> getBalances() {
        return balances != null ? balances.getBalances() : null;
    }

    public List<ProductAttributePojo> getAttributes() {
        return attributes != null ? attributes.getAttributes() : null;
    }

    public void setCodeAvarda(String codeAvarda) {
        this.codeAvarda = codeAvarda;
    }

    public String getCodeAvarda() {
        return codeAvarda;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryId() {
        return countryId;
    }
}
