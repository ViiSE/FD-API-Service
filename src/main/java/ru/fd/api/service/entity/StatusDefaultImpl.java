package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductStatusPojo;
import ru.fd.api.service.data.StatusPojo;

@Component("statusDefault")
@Scope("prototype")
public class StatusDefaultImpl implements Status {

    private final String statusId;
    private final String name;

    public StatusDefaultImpl(String statusId, String name) {
        this.statusId = statusId;
        this.name = name;
    }

    @Override
    public Object formForSend() {
        return new StatusPojo(statusId, name);
    }
}
