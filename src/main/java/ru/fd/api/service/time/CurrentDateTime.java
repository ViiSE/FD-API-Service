package ru.fd.api.service.time;

public interface CurrentDateTime {
    // yyyy-MM-dd HH:mm:ss
    String dateTimeInStandardFormat();
    // "dd.MM.yyyy HH:mm:ss"
    String dateTimeWithDot();
    // "dd.MM.yyyy"
    String dateWithDot();
    //dd-MM-yyyy
    String dateLog();
}
