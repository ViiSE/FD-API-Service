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
 */
package ru.fd.api.service.time;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author ViiSE
 */
@Component("currentDateTimeDefault")
public class CurrentDateTimeDefaultImpl implements CurrentDateTime {

    @Override
    public String dateTimeInStandardFormat() {
        return null;
    }

    @Override
    public String dateTimeWithDot() {
        return formatDate("dd.MM.yyyy HH:mm:ss");
    }

    @Override
    public String dateWithDot() {
        return formatDate("dd.MM.yyyy");
    }

    @Override
    public String dateLog() {
        return formatDate("dd-MM-yyyy");
    }


    private String formatDate(String format) {
        SimpleDateFormat nice = new SimpleDateFormat(format);
        GregorianCalendar cal = new GregorianCalendar();
        Date date = cal.getTime();
        return nice.format(date);
    }
}
