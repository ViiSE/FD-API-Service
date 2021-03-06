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

public class OrderStatuses {

    public static final short NOT_PROCESSED = 0;
    public static final short READY_TO_ASSEMBLY = 1;
    public static final short LACK_OF_PRODUCTS_QUANTITY = 2;
    public static final short ASSEMBLED = 3;
    public static final short CANCELLED = 4;
    public static final short COMPLETED = 5;
    public static final short INTERNAL_SERVER_ERROR = 500;
    public static final short BAD_REQUEST = 400;
}
