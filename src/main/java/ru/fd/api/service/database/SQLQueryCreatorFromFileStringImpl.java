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

package ru.fd.api.service.database;

import org.springframework.stereotype.Service;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.ReaderException;
import ru.fd.api.service.os.FDAPIServiceDirectory;
import ru.fd.api.service.producer.database.SQLQueryProducer;
import ru.fd.api.service.producer.database.SQLReaderProducer;

@Service("sqlQueryCreatorFromFileString")
public class SQLQueryCreatorFromFileStringImpl implements SQLQueryCreator<String, String> {

    private final FDAPIServiceDirectory serviceDirectory;
    private final SQLReaderProducer sqlReaderProducer;
    private final SQLQueryProducer sqlQueryProducer;

    public SQLQueryCreatorFromFileStringImpl(
            FDAPIServiceDirectory serviceDirectory,
            SQLReaderProducer sqlReaderProducer,
            SQLQueryProducer sqlQueryProducer) {
        this.serviceDirectory = serviceDirectory;
        this.sqlReaderProducer = sqlReaderProducer;
        this.sqlQueryProducer = sqlQueryProducer;
    }

    @Override
    public SQLQuery<String> create(String source) throws CreatorException {
        try {
            return sqlReaderProducer.getSQLReaderFromFileStringInstance(serviceDirectory, sqlQueryProducer, source).read();
        } catch (ReaderException ex) {
            throw new CreatorException(ex.getMessage(), ex.getCause());
        }
    }
}
