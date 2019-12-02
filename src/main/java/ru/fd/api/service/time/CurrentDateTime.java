package ru.fd.api.service.time;

public interface CurrentDateTime {
    // "dd.MM.yyyy HH:mm:ss"
    String dateTimeWithDot();
    // "dd.MM.yyyy"
    String dateWithDot();
    //dd-MM-yyyy
    String dateLog();
}
