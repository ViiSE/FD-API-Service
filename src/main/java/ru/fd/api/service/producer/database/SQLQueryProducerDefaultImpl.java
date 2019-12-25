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

package ru.fd.api.service.producer.database;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.database.SQLQuery;
import ru.fd.api.service.database.SQLQueryStringImpl;

@Service("sqlQueryProducerDefault")
public class SQLQueryProducerDefaultImpl implements SQLQueryProducer {

    private final ApplicationContext ctx;

    public SQLQueryProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public SQLQuery<String> getSQLQueryStringInstance(String content) {
        return (SQLQueryStringImpl) ctx.getBean("sqlQueryString", content);
    }
}
