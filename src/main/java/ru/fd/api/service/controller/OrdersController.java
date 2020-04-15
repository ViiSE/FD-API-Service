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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.data.OrderResponsePojo;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.ProcessException;
import ru.fd.api.service.process.Process;

@Api(tags="Orders Controller", description = "Контроллер точек для работы с заказами", authorizations = {@Authorization(value = "Bearer")})
@RestController
public class OrdersController {

    private final Process<OrderResponse, OrderPojo> chain;

    public OrdersController(
            @Qualifier("psChainLgCreateOrder") Process<OrderResponse, OrderPojo> chain) {
        this.chain = chain;
    }

    @ApiOperation(value = "Создает заказ (ТЕСТОВАЯ ВЕРСИЯ)")
    @PostMapping("/orders")
    public OrderResponsePojo createOrder(
            @ApiParam(required = true, value = "Поля <code><b>order{id}</b></code>, " +
                    "<code><b>order{city_id}</b></code></pre>, <code><b>order{customer}</b></code>, " +
                    "<code><b>order{delivery}</b></code>, <code><b>order{pay_type_id}</b></code> и " +
                    "<code><b>order{products[]}</b></code> являются обязательными. При их отсутствии API пришлет " +
                    "ответ со статусом <code><b>400</b></code> и соответствующим сообщением." +
                    "\nID заказа <code><b>order{id}</b></code> является ID заказа сайта." +
                    "\nВ данных покупателя <code><b>order{customer}</b></code> тип покупателя " +
                    "<code><b>order{customer{type}}</b></code> является обязательным полем. Это поле имеет следующие " +
                    "значения:" +
                    "\n<span style=\"margin-left:2em\"><b>0</b> - физическое лицо,</span>" +
                    "\n<span style=\"margin-left:2em\"><b>1</b> - юридическое лицо.</span>" +
                    "\nЕсли покупатель является юридическим лицом (<b>1</b>), то также необходимо указать поля ИНН " +
                    "<code><b>order{customer{inn}}</b></code> и КПП <code><b>order{customer{kpp}}</b></code>. " +
                    "Проверку на соответствие значений этих полей на паттерны должно быть осуществлено на стороне " +
                    "сайта." +
                    "\nМожно указывать либо номер телефона покупателя " +
                    "<code><b>order{customer{phone_number}}</b></code>, либо электронную почту " +
                    "<code><b>order{customer{email}}</b></code>, либо все вместе, но если ни одного из этих полей " +
                    "нет, то API пришлет ответ со статусом <code><b>400</b></code> и соответствующим " +
                    "сообщением. Проверка значения этих полей на соответствие паттернов должно быть осуществлено на " +
                    "стороне сайта." +
                    "\nИмя покупателя <code><b>order{customer{name}}</b></code> необязательно и может быть " +
                    "представлено в любом виде: или <i>Имя</i>, или <i>Имя Фамилия</i>, или <i>ФИО</i>." +
                    "\nТип доставки <code><b>order{delivery{type}}</b></code> является обязательным полем. Это поле " +
                    "имеет следующие значения:" +
                    "\n<span style=\"margin-left:2em\"><b>0</b> - самовывоз,</span>" +
                    "\n<span style=\"margin-left:2em\"><b>1</b> - доставка на дом.</span>" +
                    "\nЕсли тип доставки - самовывоз (<b>0</b>), то поле " +
                    "<code><b>order{delivery{department_id}}</b></code> обязательно. При его отсутствии API пришлет " +
                    "ответ со статусом <code><b>400</b></code> и соответствующим сообщением." +
                    "\nЕсли тип доставки - доставка на дом (<b>1</b>), то поля " +
                    "<code><b>order{delivery{address}}</b></code>, " +
                    "<code><b>order{delivery{delivery_time_id}}</b></code> и " +
                    "<code><b>order{delivery{delivery_date}}</b></code> являются обязательными. При их отсутствии " +
                    "API пришлет ответ со статусом <code><b>400</b></code> и соответствующим сообщением." +
                    "\nТип времени доставки <code><b>order{delivery{delivery_time_id}}</b></code> имеет следующее " +
                    "значение: " +
                    "\n<span style=\"margin-left:2em\"><b>0</b> - с 9 до 14,</span>" +
                    "\n<span style=\"margin-left:2em\"><b>1</b> - с 14 до 19.</span>" +
                    "\nGID города доставки <code><b>order{delivery{city_id}}</b></code> является обязательным полем, " +
                    "при его отсутствии API пришлет ответ со статусом <code><b>400</b></code> и соответствующим " +
                    "сообщением." +
                    "\nТип оплаты заказа покупателем <code><b>order{pay_type_id}</b></code> имеет следующие " +
                    "значения: " +
                    "\n<span style=\"margin-left:2em\"><b>0</b> - оплата на месте,</span>" +
                    "\n<span style=\"margin-left:2em\"><b>1</b> - оплата на сайте по безналичному расчету.</span>" +
                    "\nДата и время оформления доставки <code><b>order{date_time}</b></code> записывается в формате " +
                    "<code><b>yyyy-MM-dd HH:mm:ss</b></code>. Если это поле не будет указано, то будет применено " +
                    "значение по-умолчанию (текущие дата и время).\n" +
                    "Коментарий покупателя к заказу <code><b>order{comment}</b></code> необязателен, может иметь все " +
                    "допустимые символы." +
                    "\nСписок товаров заказа <code><b>order{products[]}</b></code> не может быть пустым, иначе API " +
                    "пришлет ответ со статусом <code><b>400</b></code> и соответствующим сообщением. Каждый товар в " +
                    "списке должен иметь GID товара <code><b>order{products[product{id}]}</b></code> и количество " +
                    "этого заказанного товара <code><b>order{products[product{quantity}]}</b></code>." +
                    "\nВ случае успешного создания заказа (статус HTTP <code><b>200</b></code>) ответ будет состоять " +
                    "из сообщения <code><b>order_response{message}</b></code>, id заказа сайта " +
                    "<code><b>order_response{id}</b></code>, и статуса заказа " +
                    "<code><b>order_response{status}</b></code>." +
                    "\n___" +
                    "\n<b><i>При создании нового заказа указывать</i></b> <code><b>order{status}</b></code> <b><i>не обязательно!</b></i>")
            @RequestBody OrderPojo orderPojo) {
        try {
            return (OrderResponsePojo) chain.answer(orderPojo).formForSend();
        } catch (ProcessException ex) {
            throw ex.getRuntimeException();
        }
    }

    // TODO: 22.01.2020 CREATE IN FUTURE
//    @ApiOperation(value = "Выгружает заказы")
//    @GetMapping("/orders")
//    public OrdersPojo getOrders(
//            @ApiParam(required = true, value = "")
//            @RequestParam
//            List<Long> idList) {
//        try {
//            Orders orders = (Orders) ordersService.orderRepositoryProcessors()
//                    .processor(Processors.GET_ORDERS)
//                    .apply(idList);
//            OrderResponsePojo orderResponsePojo = (OrderResponsePojo) orderResponse.formForSend();
//
//            if(orderResponsePojo.getStatus() == OrderStatuses.INTERNAL_SERVER_ERROR) {
//                logger.error(DepartmentsController.class, orderResponsePojo.getExMessage());
//                throw new CreateOrderException(orderResponsePojo.getMessage());
//            } else {
//                logger.info(OrdersController.class, "Order " + orderResponsePojo.getId() + " was created");
//                return orderResponsePojo;
//            }
//        } catch (CreatorException ex) {
//            logger.error(DepartmentsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
//            throw new CreateOrderException(ex.getMessage());
//        }
//        return new OrdersPojo(new ArrayList<>());
//    }
}
