package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.StatusPojo;
import ru.fd.api.service.data.StatusesPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("statusesDefault")
@Scope("prototype")
public class StatusesDefaultImpl implements Statuses {

    private final List<Status> statuses;

    public StatusesDefaultImpl(List<Status> statuses) {
        this.statuses = statuses;
    }

    @Override
    public Object formForSend() {
        List<StatusPojo> statusPojos = statuses.stream()
                .map(status -> (StatusPojo) status.formForSend())
                .collect(Collectors.toList());
        return new StatusesPojo(statusPojos);
    }
}
