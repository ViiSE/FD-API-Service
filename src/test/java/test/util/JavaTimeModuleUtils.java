/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package test.util;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JavaTimeModuleUtils {

    private JavaTimeModule module;

    public JavaTimeModuleUtils createJavaTimeModule() {
        module = new JavaTimeModule();
        return JavaTimeModuleUtils.this;
    }

    public JavaTimeModuleUtils addLocalDateTimeSerializer(String pattern) {
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
        module.addSerializer(LocalDateTime.class, localDateTimeSerializer);

        return this;
    }

    public JavaTimeModuleUtils addLocalDateTimeDeserializer(String pattern) {
        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern));
        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);

        return this;
    }

    public JavaTimeModuleUtils addLocalDateDeserializer(String pattern) {
        LocalDateDeserializer localDateDeserializer = new LocalDateDeserializer(DateTimeFormatter.ofPattern(pattern));
        module.addDeserializer(LocalDate.class, localDateDeserializer);

        return this;
    }

    public JavaTimeModuleUtils addLocalDateSerializer(String pattern) {
        LocalDateSerializer localDateSerializer = new LocalDateSerializer(DateTimeFormatter.ofPattern(pattern));
        module.addSerializer(LocalDate.class, localDateSerializer);

        return this;
    }

    public JavaTimeModule javaTimeModule() {
        return module;
    }
}
