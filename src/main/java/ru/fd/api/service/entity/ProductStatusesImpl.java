package ru.fd.api.service.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.fd.api.service.data.ProductStatusPojo;
import ru.fd.api.service.data.StatusesPojo;

import java.util.List;
import java.util.stream.Collectors;

@Component("productStatusesDefault")
@Scope("prototype")
public class ProductStatusesDefaultImpl implements Statuses {

    private final List<Status> statuses;

    public ProductStatusesDefaultImpl(List<Status> statuses) {
        this.statuses = statuses;
    }

    @Override
    public Object formForSend() {
        List<ProductStatusPojo> statusPojos = statuses.stream()
                .map(status -> (ProductStatusPojo) status.formForSend())
                .collect(Collectors.toList());
        return new StatusesPojo(statusPojos);
    }
}
