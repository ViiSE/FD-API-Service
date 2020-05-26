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

package ru.fd.api.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fd.api.service.data.ProductsChangedBalancesPojo;
import ru.fd.api.service.data.ProductsOfferPojo;
import ru.fd.api.service.data.ProductsPojo;
import ru.fd.api.service.entity.Products;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.process.Process;

import java.util.ArrayList;
import java.util.List;

@Api(tags= "Products Controller", description = "Контроллер точек для работы с товарами", authorizations = {@Authorization(value = "Bearer")})
@RestController
public class ProductsController {

    private final Process<Products, List<String>> chain;
    private final Process<Products, Void> processCBalanceAll;
    private final Process<Products, Long> processCBalanceOrder;
    private final Process<Products, Void> processProductsOffer;


    public ProductsController(
            @Qualifier("psChainLgProducts") Process<Products, List<String>> chain,
            @Qualifier("psLgChangedBalances") Process<Products, Void> processCBalanceAll,
            @Qualifier("psLgChangedBalancesWithOrder") Process<Products, Long> processCBalanceOrder,
            @Qualifier("psLgProductsOffer") Process<Products, Void> processProductsOffer) {
        this.chain = chain;
        this.processCBalanceAll = processCBalanceAll;
        this.processCBalanceOrder = processCBalanceOrder;
        this.processProductsOffer = processProductsOffer;
    }

    @ApiOperation(value = "Выгружает все товары")
    @GetMapping("/products")
    public ProductsPojo products(
            @ApiParam(value = "При указании данного параметра товары будут выгружаться с указанными зависимостями.\n" +
                    "Доступные зависимости:" +
                    "\n<span style=\"margin-left:2em\"><b>balances</b> - остатки товара," +
                    "\n<span style=\"margin-left:2em\"><b>prices</b> - цены товара,</span>" +
                    "\n<span style=\"margin-left:2em\">[!!!НЕДОСТУПНО!!!] <b>statuses</b> - статусы товара,</span>" +
                    "\n<span style=\"margin-left:2em\"><b>attributes</b> - атрибуты товара</span>" +
                    "\nНесуществующие зависимости, указанные в запросе, будут проигнорированы.\n___" +
                    "\n<i>Примечание: Выгружаемый товар без зависимости</i> <b>prices</b> <i>может иметь нулевую цену.</i>")
            @RequestParam(required = false) List<String> with) {
        try {
            if(with == null)
                with = new ArrayList<>();
            return (ProductsPojo) chain.answer(with).formForSend();
        } catch (ProcessException ex) {
            return new ProductsPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "Выгружает товары с измененными остатками (ТЕСТОВАЯ ВЕРСИЯ)")
    @GetMapping("/products/balances/changes")
    public ProductsChangedBalancesPojo changedBalances(
            @ApiParam(value = "При указании параметра <code><b>order_id</code></b> метод вернет изменения остатков " +
                    "только тех товаров, которые были указаны в данном заказе. Без этого параметра метод вернет " +
                    "изменения остатков всех товаров." +
                    "\n___" +
                    "\n<i>Примечание. Метод может иметь задержку, начиная от 500 мс.</i>", example = "1")
            @RequestParam(name = "order_id", defaultValue = "-1") long orderId) {
        try {
            if(orderId == -1)
                return (ProductsChangedBalancesPojo) processCBalanceAll.answer(null).formForSend();
            else
                return (ProductsChangedBalancesPojo) processCBalanceOrder.answer(orderId).formForSend();
        } catch (ProcessException ex) {
            return new ProductsChangedBalancesPojo(new ArrayList<>());
        }
    }

    @ApiOperation(value = "Выгружает товары, участвующие в акции")
    @GetMapping("/products/offers")
    public ProductsOfferPojo productsOffer() {
        try {
            return (ProductsOfferPojo) processProductsOffer.answer(null).formForSend();
        } catch (ProcessException ex) {
            return new ProductsOfferPojo(new ArrayList<>());
        }
    }
}
