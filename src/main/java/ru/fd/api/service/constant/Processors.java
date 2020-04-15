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

package ru.fd.api.service.constant;

public class Processors {

    @Deprecated public static final String CREATE_ORDER_DEPRECATED = "create_order_deprecated";
    public static final String CREATE_ORDER = "create_order";
    public static final String GET_ORDERS = "get_orders";
    public static final String CHANGED_BALANCES_WITH_ORDER_ID = "changed_balances_with_order_id";
    public static final String CHANGED_BALANCES = "changed_balances";
}
