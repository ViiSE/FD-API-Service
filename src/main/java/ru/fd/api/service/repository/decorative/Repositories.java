package ru.fd.api.service.repository.decorative;

public interface Repositories<T> {
    T repo(String key);
}
