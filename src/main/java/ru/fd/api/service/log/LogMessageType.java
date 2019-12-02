package ru.fd.api.service.log;

public enum LogMessageType {
    INFO {
        @Override
        public String stringValue() { return "Info"; }
    },
    ERROR {
        @Override
        public String stringValue() { return "Error"; }
    };

    public abstract String stringValue();
}
