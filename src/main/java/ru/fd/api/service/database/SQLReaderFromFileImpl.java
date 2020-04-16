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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.exception.ReaderException;
import ru.fd.api.service.os.FDAPIServiceDirectory;
import ru.fd.api.service.producer.database.SQLQueryProducer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component("sqlReaderFromFile")
@Scope("prototype")
public class SQLReaderFromFileImpl implements SQLReader<String> {

    private final FDAPIServiceDirectory serviceDirectory;
    private final SQLQueryProducer sqlQueryProducer;
    private final String filename;

    public SQLReaderFromFileImpl(FDAPIServiceDirectory serviceDirectory, SQLQueryProducer sqlQueryProducer, String filename) {
        this.serviceDirectory = serviceDirectory;
        this.sqlQueryProducer = sqlQueryProducer;
        this.filename = filename;
    }

    @Override
    public SQLQuery<String> read() throws ReaderException {
        StringBuilder content = new StringBuilder();

        File file = new File(serviceDirectory.directory() + "sql" + File.separator + filename);
        try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String st;
            while ((st = br.readLine()) != null)
                content.append(st).append("\n");
        } catch (IOException ex) {
            throw new ReaderException(ex.getMessage(), ex.getCause());
        }

        return sqlQueryProducer.getSQLQueryStringInstance(content.toString());
    }
}
