/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.controller;

import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.fd.api.service.OrdersService;
import ru.fd.api.service.constant.Processors;
import ru.fd.api.service.data.OrderPojo;
import ru.fd.api.service.data.OrderResponsePojo;
import ru.fd.api.service.entity.Order;
import ru.fd.api.service.entity.OrderResponse;
import ru.fd.api.service.exception.CreateOrderException;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.log.LoggerService;

@Controller("/orders")
@Api(tags="Orders Controller", description = "Контроллер точек для работы с заказами", authorizations = {@Authorization(value = "Bearer")})
public class OrdersController {

    private final OrdersService ordersService;

    private final LoggerService logger;

    public OrdersController(OrdersService ordersService, LoggerService logger) {
        this.ordersService = ordersService;
        this.logger = logger;
    }

    @ApiOperation(value = "Создает заказ")
    @PostMapping
    @ResponseBody
    public OrderResponsePojo createOrder(
            @ApiParam(required = true, value = "Поля <code><b>order{id}</b></code>, " +
                    "<code><b>order{city_id}</b></code></pre>, <code><b>order{customer}</b></code>, " +
                    "<code><b>order{delivery}</b></code>, <code><b>order{pay_type_id}</b></code> и " +
                    "<code><b>order{products[]}</b></code> являются обязательными. При их отсутствии API пришлет " +
                    "ответ со статусом <code><b>500</b></code> и соответствующим сообщением." +
                    "\nID заказа <code><b>order{id}</b></code> является ID заказа сайта." +
                    "\nВ данных покупателя <code><b>order{customer}</b></code> тип покупателя " +
                    "<code><b>order{customer{type}}</b></code> является обязательным полем. Это поле имеет следующие " +
                    "значения:" +
                    "\n<span style=\"margin-left:2em\"><b>0</b> - физическое лицо,</span>" +
                    "\n<span style=\"margin-left:2em\"><b>1</b> - юридическое лицо.</span>" +
                    "\nЕсли покупатель является юридическим лицом (<b>1</b>), то также необходимо указать поля ИНН " +
                    "<code><b>order{customer{inn}}</b></code> и КПП <code><b>order{customer{kpp}}</b></code>. " +
                    "Проверку на заполнение этих полей и соответствие их значений на паттерны должно быть " +
                    "осуществлено на стороне сайта." +
                    "\nМожно указывать либо номер телефона покупателя " +
                    "<code><b>order{customer{phone_number}}</b></code>, либо электронную почту " +
                    "<code><b>order{customer{email}}</b></code>, либо все вместе, но если ни одного из этих полей " +
                    "нет, то API пришлет ответ со статусом <code><b>500</b></code> и соответствующим " +
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
                    "ответ со статусом <code><b>500</b></code> и соответствующим сообщением." +
                    "\nЕсли тип доставки - доставка на дом (<b>1</b>), то поля " +
                    "<code><b>order{delivery{address}}</b></code>, " +
                    "<code><b>order{delivery{delivery_time_id}}</b></code> и " +
                    "<code><b>order{delivery{delivery_date}}</b></code> являются обязательными. При их отсутствии " +
                    "API пришлет ответ со статусом <code><b>500</b></code> и соответствующим сообщением." +
                    "\nТип времени доставки <code><b>order{delivery{delivery_time_id}}</b></code> имеет следующее " +
                    "значение: " +
                    "\n<span style=\"margin-left:2em\"><b>0</b> - с 9 до 14,</span>" +
                    "\n<span style=\"margin-left:2em\"><b>1</b> - с 14 до 19.</span>" +
                    "\nGID города доставки <code><b>order{delivery{city_id}}</b></code> является обязательным полем, " +
                    "при его отсутствии API пришлет ответ со статусом <code><b>500</b></code> и соответствующим " +
                    "сообщением." +
                    "\nТип оплаты заказа покупателем <code><b>order{pay_type_id}</b></code> имеет следующие " +
                    "значения: " +
                    "\n<span style=\"margin-left:2em\"><b>0</b> - оплата на месте,</span>" +
                    "\n<span style=\"margin-left:2em\"><b>1</b> - оплата на сайте по безналичному расчету.</span>" +
                    "\nДата и время оформления доставки <code><b>order{date_time}</b></code> записывается в формате " +
                    "<code><b>yyyy-MM-dd HH:mm:ss</b></code>. Если это поле не будет указано, то будет применено значение по-умолчанию " +
                    "(текущие дата и время).\n" +
                    "Коментарий покупателя к заказу <code><b>order{comment}</b></code> необязателен, может иметь все " +
                    "допустимые символы." +
                    "\nСписок товаров заказа <code><b>order{products[]}</b></code> не может быть пустым, иначе API " +
                    "пришлет ответ со статусом <code><b>500</b></code> и соответствующим сообщением. Каждый товар в " +
                    "списке должен иметь GID товара <code><b>order{products[product{id}]}</b></code> и количество " +
                    "этого заказанного товара <code><b>order{products[product{quantity}]}</b></code>." +
                    "\nВ случае успешного создания заказа (статус HTTP <code><b>200</b></code>) ответ будет состоять из " +
                    "сообщения <code><b>order_response{message}</b></code>, id заказа сайта " +
                    "<code><b>order_response{id}</b></code>, и статуса <code><b>order_response{status}</b></code>." +
                    "\n___" +
                    "\n<i>Примечание: В данной реализации сайт должен обновлять свои остатки самостоятельно. Через " +
                    "некоторое время (данное время будет выбрано совместно) необходимо сделать GET-запрос обновления " +
                    "изменений остатков товаров [TODO: Написать данный GET запрос].")
            @RequestBody OrderPojo orderPojo) {
        try {
            Order order = ordersService.orderCreatorProducer()
                    .getOrderCreatorFromBodyInstance(
                            orderPojo,
                            ordersService)
                    .create();
            OrderResponse orderResponse = ordersService.orderRepositoryProcessors()
                    .processor(Processors.CREATE_ORDER_WITHOUT_CHECK_STATUS)
                    .apply(order);
            OrderResponsePojo orderResponsePojo = (OrderResponsePojo) orderResponse.formForSend();

            if(orderResponsePojo.getStatus() == 500) {
                logger.error(DepartmentsController.class, orderResponsePojo.getExMessage());
                throw new CreateOrderException(orderResponsePojo.getMessage());
            } else {
                logger.info(OrdersController.class, "Order " + orderResponsePojo.getId() + " was created");
                return orderResponsePojo;
            }
        } catch (CreatorException ex) {
            logger.error(DepartmentsController.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
            throw new CreateOrderException(ex.getMessage());
        }
    }
}
