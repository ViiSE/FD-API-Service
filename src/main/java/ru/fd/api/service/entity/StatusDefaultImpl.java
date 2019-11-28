package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.StatusPojo;

@Component("statusDefault")
@Scope("prototype")
public class StatusDefaultImpl implements Status {

    private final String departmentId;
    private final String statusId;

    public StatusDefaultImpl(String departmentId, String statusId) {
        this.departmentId = departmentId;
        this.statusId = statusId;
    }

    @Override
    public Object formForSend() {
        return new StatusPojo(departmentId, statusId);
    }
}
