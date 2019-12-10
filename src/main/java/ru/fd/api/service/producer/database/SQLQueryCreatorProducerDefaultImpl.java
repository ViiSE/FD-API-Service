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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.database.SQLQueryCreatorFromFileStringImpl;
import ru.fd.api.service.util.FDAPIServiceDirectory;

@Service("sqlQueryCreatorProducerDefault")
public class SQLQueryCreatorProducerDefaultImpl implements SQLQueryCreatorProducer {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public SQLQueryCreator<String, String> getSQLQueryCreatorFromFileStringInstance(
            FDAPIServiceDirectory serviceDirectory,
            SQLReaderProducer sqlReaderProducer,
            SQLQueryProducer sqlQueryProducer) {
        return (SQLQueryCreatorFromFileStringImpl) ctx.getBean(
                "sqlQueryCreatorFromFileString",
                serviceDirectory,
                sqlReaderProducer,
                sqlQueryProducer);
    }
}